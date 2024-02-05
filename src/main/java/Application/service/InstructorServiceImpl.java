package Application.service;

import Application.dao.CourseDAO;
import Application.dao.InstructorDAO;
import Application.entity.Course;
import Application.entity.Instructor;
import Application.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InstructorServiceImpl  implements  InstructorService{

    private InstructorDAO instructorDAO;
    private CourseDAO courseDAO;
    @Autowired
    public InstructorServiceImpl(InstructorDAO instructorDAO, CourseDAO courseDAO) {
        this.instructorDAO = instructorDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public void save(Instructor theInstructor) {
        instructorDAO.save(theInstructor);
    }

    @Override
    public void update(Instructor theInstructor, int id) {
        try {
            instructorDAO.update(theInstructor, id);
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @Override
    public List<Instructor> getAllInstructor() {
        return instructorDAO.findAllInstructor();
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return instructorDAO.findInstructorById(theId);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return instructorDAO.findInstructorDetailById(theId);
    }

    @Override
    public void deleteInstructorDetailById(int theId) {
        try {
            instructorDAO.deleteInstructorDetailById(theId);
        } catch (Exception exception) {
            throw new NullPointerException(exception.getMessage());
        }
    }

    @Override
    public void deleteInstructorById(int theId) {
        try {
            instructorDAO.deleteInstructorById(theId);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    @Override
    public void updateInstructorDetailById(int theId, InstructorDetail instructorDetail) {
        try {
            instructorDAO.updateInstructorDetailById(theId, instructorDetail);
        }catch(Exception exc){
            throw new IllegalArgumentException("Invalid Instructor ID");
        }
    }


    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        try{


            return instructorDAO.findCoursesByInstructorId(theId);

        }catch(Exception e){

            throw e;
        }
    }
    @Override
    public void uploadCourseByInstructorId(int instructorId, Course c){

        Instructor instructor  = instructorDAO.findInstructorById(instructorId);

        if(instructor == null){
            throw new NullPointerException("Sorry the Instructor doesn't existed");
        }

        instructor.getCourses().add(c);
        c.setInstructor(instructor);
        instructorDAO.update(instructor, instructorId);
    }
}
