package Application.dao;

import Application.entity.Course;
import Application.entity.Instructor;
import Application.entity.Review;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO{

    EntityManager entityManager;
    @Autowired
    public CourseDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public List<Course> findCourses() {
        return entityManager.createQuery("from Course", Course.class).getResultList();
    }

    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {

        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        Course temp = entityManager.find(Course.class, theId);
        if(temp == null)
            throw new NullPointerException("Can not find the Course");


        return temp;

    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        try{

            Course c = findCourseById(theId);
            c.setInstructor(null);

            entityManager.remove(c);
        }
        catch(Exception e){

//          TODO: When the course is registered by certain student, you can not delete the course
//
            System.out.println("My class : " + e.getMessage());
            throw e;
        }

    }

    @Override
    public List<Review> getCourseReview(int id) {

        try{
            Course c = findCourseById(id);
            return c.getReviews();
        }
        catch (Exception e){


            throw e;
        }

    }
}
