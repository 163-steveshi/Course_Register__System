package Application.service;

import Application.entity.Course;
import Application.entity.Review;

import java.util.List;

public interface CourseService {



    List<Course> findCourses();
    Course findCourseById(int Id);
    void deleteCourseById(int id);
    void updateCourseById(int id, Course c);
    List<Review> getCourseReviewsById(int id);
}
