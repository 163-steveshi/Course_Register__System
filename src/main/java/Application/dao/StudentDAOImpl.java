package Application.dao;

import Application.entity.Course;
import Application.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;
    @Autowired
    public StudentDAOImpl(EntityManager e) {
        this.entityManager = e;
    }

    @Override
    @Transactional
    public void saveStudent(Student stu) {
        stu.setId(0);
        entityManager.persist(stu);
    }

    @Override
    @Transactional
    public void updateStudent(Student stu, int id) {
        try{
            findStudentById(id);
            stu.setId(id);
//          TODO: check if update student information will lead to remove the course that previous taken
            entityManager.merge(stu);

        }
        catch(Exception e){


            throw e;
        }
    }

    @Override
    public Student findStudentById(int stuId) {
       Student stu  = entityManager.find(Student.class, stuId);

       if(stu == null){

           throw new NullPointerException("Sorry the student you search doesn't existed");


       }

       return stu;
    }

    @Override
    public List<Student> findAllStudents() {

        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s ", Student.class);
//        THE SQL QUERY HAS BUG
        return  query.getResultList();

    }

    @Override
    @Transactional
    public void deleteStudentById(int stuId) {
        try{

            Student stu = findStudentById(stuId);
            stu.getCR();
            stu.removeAllCourseRegistration();

            String temp = "After remove :";
            System.out.printf(temp);
            stu.getCR();
            System.out.println("delete the student");
            entityManager.remove(stu);


        }catch(NullPointerException exc){


            throw exc;
        }
    }

    @Override
    @Transactional
    public void registerCourseForStu(int stuId, Course c) {

        try{
            Student stu = findStudentById(stuId);
            stu.addCourseRegistration(c);
            entityManager.merge(stu);
        }catch(NullPointerException exc){


            throw exc;
        }

    }

    @Override
    public void removeCourseForStu(int courseId, int stuId) {

    }

    @Override
    public List<Course> findAllRegisteredCourseByStudentId(int stuId) {
        return null;
    }
}
