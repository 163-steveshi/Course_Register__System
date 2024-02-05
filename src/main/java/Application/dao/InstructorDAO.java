package Application.dao;

import java.util.List;

import Application.entity.Course;
import Application.entity.Instructor;
import Application.entity.InstructorDetail;

public interface InstructorDAO {

    void save(Instructor theInstructor);
    void update(Instructor instructor, int id);
    Instructor findInstructorById(int theId);
    List<Instructor> findAllInstructor();
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void updateInstructorDetailById(int theId, InstructorDetail instructorDetail);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);


}
