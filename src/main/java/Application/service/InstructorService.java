package Application.service;
import java.util.List;

import Application.entity.Course;
import Application.entity.Instructor;
import Application.entity.InstructorDetail;

public interface InstructorService {

    void save(Instructor theInstructor);
    void update(Instructor theInstructor, int id);
    List<Instructor> getAllInstructor();
    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    void updateInstructorDetailById(int theId, InstructorDetail instructorDetail);

    List<Course> findCoursesByInstructorId(int theId);
    void uploadCourseByInstructorId(int instructorId, Course c);

}
