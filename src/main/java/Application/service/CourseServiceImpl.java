package Application.service;

import Application.dao.CourseDAO;
import Application.entity.Course;
import Application.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{


    private CourseDAO courseDAO;
    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Course> findCourses() {
        return courseDAO.findCourses();
    }

    @Override
    public Course findCourseById(int Id) {
        try{
            return courseDAO.findCourseById(Id);
        }catch(Exception exc) {
            throw exc;
        }
    }

    @Override
    public void deleteCourseById(int id) {
        try{
           courseDAO.deleteCourseById(id);
        }catch(Exception exc) {
            throw exc;
        }
    }

    @Override

    public void updateCourseById(int id, Course c) {


        Course temp  =courseDAO.findCourseById(id);

        if (temp == null){

            throw new NullPointerException("Sorry, the Course You want to modify doesn't existed");

        }
        temp.setTitle(c.getTitle());
        courseDAO.updateCourse(temp);

    }

    @Override
    public List<Review> getCourseReviewsById(int id) {

        try{
            return courseDAO.getCourseReview(id);

        }catch( Exception e){

            throw e;
        }
    }
}
