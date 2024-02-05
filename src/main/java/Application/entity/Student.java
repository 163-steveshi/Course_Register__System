package Application.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name= "student")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int Id;

    @Column(name= "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CourseRegistration> courseRegistrations;

    public Student(){}
    public Student(String firstName, String lastName, String email) {
        super(firstName, lastName, email);

        courseRegistrations = new HashSet<>();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addCourseRegistration(Course course) {
        CourseRegistration newRegistration = new CourseRegistration(this, course);
        this.courseRegistrations.add(newRegistration);
    }

    public void removeCourseRegistration(Course course) {
        courseRegistrations.removeIf(registration -> registration.getCourse().equals(course));
    }

    public void getCR(){

        for(CourseRegistration cr: courseRegistrations){

            System.out.println(cr);
        }
    }
    public void removeAllCourseRegistration(){


        courseRegistrations.removeIf(registration -> registration.getStudentId() == this.Id);
    }

    public void  setCourseRegistration(Set<CourseRegistration> cr){


        this.courseRegistrations  = cr;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
