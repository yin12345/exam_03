package edu.henu.exam_03.exam_03.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    private String stu_id;
    private String stu_name;
    private String stu_class;
    private String stu_submit;
    private String stu_ip;
    private Integer stu_exam;
    @ManyToMany(fetch = FetchType.LAZY,targetEntity = Exam.class)
    @JoinTable(name="OA_ID_EXAM_STUDENT", joinColumns=@JoinColumn(name="STUDENT_ID", referencedColumnName="stu_id"),
            inverseJoinColumns=@JoinColumn(name="EXAM_ID", referencedColumnName="eid"))
    private List<Exam> exams ;

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getStu_submit() {
        return stu_submit;
    }

    public void setStu_submit(String stu_submit) {
        this.stu_submit = stu_submit;
    }

    public String getStu_ip() {
        return stu_ip;
    }

    public void setStu_ip(String stu_ip) {
        this.stu_ip = stu_ip;
    }

    public Integer getStu_exam() {
        return stu_exam;
    }

    public void setStu_exam(Integer stu_exam) {
        this.stu_exam = stu_exam;
    }
    @JsonBackReference

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
