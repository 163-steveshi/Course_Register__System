package Application.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
//@Embedded is used to embed a type into another entity.
//here we want to use StudentId and courseId as a primary key (two value pair key)
@Embeddable
public class CourseRegistrationId implements Serializable {
    private int studentId;
    private int courseId;

    public CourseRegistrationId() {}

    public CourseRegistrationId(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getters and setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRegistrationId that = (CourseRegistrationId) o;
        return studentId == that.studentId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
