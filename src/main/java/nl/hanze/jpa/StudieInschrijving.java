package nl.hanze.jpa;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "studie")
public class StudieInschrijving implements Serializable {
    @Id
    @GeneratedValue
    private long studie_id;

    @Column(name = "studie_name", nullable = false)
    private String studie_name;

    @Column(name = "school_name", nullable = false)
    private String school_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    public StudieInschrijving() {
    }

    public StudieInschrijving(String school, String studie) {
        this.school_name = school;
        this.studie_name = studie;
    }

    public String getStudie_name() {
        return studie_name;
    }

    public void setStudie_name(String studie_name) {
        this.studie_name = studie_name;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student_id) {
        this.student = student_id;
    }

    @Override
    public String toString() {
        return school_name + "\t" + studie_name + "\t" + student.getId();
    }
}
