package Application.dao;

import Application.entity.Course;
import Application.entity.Student;

import java.util.List;

public interface StudentDAO {


    void saveStudent(Student stu);
    void updateStudent(Student stu, int id);
    Student findStudentById(int stuId);
    List<Student> findAllStudents();
    void deleteStudentById(int stuId);
    void registerCourseForStu(int stuId, Course c);
    void removeCourseForStu(int courseId, int stuId);




    List<Course> findAllRegisteredCourseByStudentId(int stuId);


}
