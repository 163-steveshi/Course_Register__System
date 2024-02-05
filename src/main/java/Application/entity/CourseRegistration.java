package Application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_student")
public class CourseRegistration {

    @EmbeddedId
    private CourseRegistrationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId") // This matches the name in the CourseRegistrationId class
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId") // This matches the name in the CourseRegistrationId class
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseRegistration() {}

    public CourseRegistration(Student student, Course course) {
        // Set both the composite key and the relationships
        this.id = new CourseRegistrationId(student.getId(), course.getId());
        this.student = student;
        this.course = course;
    }

    // Getters and setters
    public CourseRegistrationId getId() {
        return id;

    }
    public int getStudentId(){

        return this.id.getStudentId();
    }

    public void setId(CourseRegistrationId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseRegistration{" +
                "id=" + id+
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}

