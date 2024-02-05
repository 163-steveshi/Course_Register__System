package Application.dao;
import Application.entity.Course;
import Application.entity.Instructor;
import Application.entity.InstructorDetail;

import java.util.List;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class InstructorDAOImpl implements InstructorDAO{

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public InstructorDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    @Transactional
    public void update(Instructor instructor, int id) {
        Instructor instr =  findInstructorById(id);
        if(instr == null)
            throw new IllegalArgumentException("Please enter a valid id");
        entityManager.merge(instructor);
    }

    @Override
    public List<Instructor> findAllInstructor(){
        return  entityManager.createQuery("from Instructor", Instructor.class).getResultList();

    }
    @Override
    public Instructor findInstructorById(int theId) {

        //use the entityManager to find
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
//        TODO: fix the logic rethrow the error for the remove non-existed entity
        Instructor instructor =  findInstructorById(theId);
        if(instructor == null)
            throw new IllegalArgumentException("Please enter a valid id");
        entityManager.remove(instructor);
    }

//    TODO:  a updateInstructorDetail function and a createInstructorDetail Function


    @Override
    @Transactional
    public void updateInstructorDetailById(int theId, InstructorDetail instructorDetail) {

        Instructor instructor =  findInstructorById(theId);
        System.out.println(instructor);

        if(instructor == null)
            throw new IllegalArgumentException("Please enter a valid id");
        System.out.println("Start Update");
        instructorDetail.setId(theId);
        instructor.setInstructorDetail(instructorDetail);
        System.out.println(instructorDetail);
        instructorDetail.setInstructor(instructor);
        entityManager.merge(instructorDetail);
        entityManager.merge(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
    // one-to-one relationship: the instructor detial id =   instructor id
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        if (tempInstructorDetail == null) {

            throw new NullPointerException("Current Instructor doesn't have the instructor detail");
        }
        //remove the associated object reference
        //break bidirectional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(tempInstructorDetail);
    }
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        Instructor instructor = entityManager.find(Instructor.class, theId);
        if (instructor == null)
            throw new NullPointerException("Sorry, the current course instructor doesn't existed");

        return instructor.getCourses();
    }
}
