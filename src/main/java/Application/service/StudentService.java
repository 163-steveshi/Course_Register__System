package Application.service;

import Application.entity.Student;

import java.util.List;

public interface StudentService {


    List<Student> findAllStudents();
    Student findStudentById(int stuId);
    void registerStudent(Student stu);
    void updateStudent(Student stu, int stuId);
    void removeStudentById(int stuId);

    void registerCourse(int stuId, int courseId);
}
