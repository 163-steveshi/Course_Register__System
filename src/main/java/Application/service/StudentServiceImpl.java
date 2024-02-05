package Application.service;

import Application.dao.CourseDAO;
import Application.dao.StudentDAO;
import Application.entity.Course;
import Application.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO, CourseDAO courseDAO) {
        this.studentDAO = studentDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDAO.findAllStudents();
    }


    @Override
    public Student findStudentById(int stuId) {
        try{
            return studentDAO.findStudentById(stuId);

        }catch(Exception e){


            throw e;
        }
    }


    @Override
    public void registerStudent(Student stu) {
        studentDAO.saveStudent(stu);
    }

    @Override
    public void updateStudent(Student stu, int stuId) {
        try{

            studentDAO.updateStudent(stu, stuId);

        }catch(Exception e){


            throw e;
        }
    }

    @Override
    public void removeStudentById(int stuId) {
        try{

            studentDAO.deleteStudentById(stuId);

        }
        catch(Exception exc){


            throw exc;
        }
    }

    @Override
    public void registerCourse(int stuId, int courseId) {

        try{

            Course c = courseDAO.findCourseById(courseId);
            studentDAO.registerCourseForStu(stuId,  c);

        }catch(NullPointerException exception){


            throw exception;

        }

    }
}
