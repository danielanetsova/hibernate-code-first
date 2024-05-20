package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {
    @Column(name = "average_grade", columnDefinition = "DECIMAL(3,2)")
    private Double averageGrade;

    @Basic
    private Boolean attendance;

    @ManyToMany(targetEntity = Course.class, mappedBy = "students")
    private Set<Course> courses;

    public Student() {}

    public Student(String firstName, String lastName, String phone_number, Double averageGrade, Boolean attendance) {
        super(firstName, lastName, phone_number);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
        this.courses = new HashSet<>();
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
