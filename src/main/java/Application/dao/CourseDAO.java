package Application.dao;

import Application.entity.Course;
import Application.entity.Instructor;
import Application.entity.Review;

import java.util.List;

public interface CourseDAO {




    List<Course> findCourses();
    void updateCourse(Course tempCourse);
    Course findCourseById(int theId);
    void deleteCourseById(int theId);
    List<Review> getCourseReview (int id);


}
