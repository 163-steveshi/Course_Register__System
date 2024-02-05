package Application.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;




@Entity
@Table(name="instructor")

public class Instructor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;

    //sett mapping between instructor and instructor detail
    @OneToOne(cascade = CascadeType.ALL)
    //reference current primary key in the instructor detail table as the foreign key
    @JoinColumn(name="instructor_detail_id")
    @JsonManagedReference
    private InstructorDetail instructorDetail;



    //ser that relation that one instructor owns many courses
    @OneToMany(mappedBy = "instructor", fetch= FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonManagedReference
    private List<Course> courses;


    public Instructor(){}

    public Instructor(String firstName, String lastName, String email) {

        super(firstName,lastName,email);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }


    //bidirectional add relationship between Course and Instructor
        public void add(Course tempCourse){


            if(courses == null){

                courses = new ArrayList<>();
            }

            courses.add(tempCourse);
            //update the relationship
            tempCourse.setInstructor(this);
        }
}
