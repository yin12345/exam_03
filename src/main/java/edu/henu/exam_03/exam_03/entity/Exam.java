package edu.henu.exam_03.exam_03.entity;




import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eid;
    private String ename;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date starttime;
    private int autostart;
    private String exampaper;
    private int started;
    private int finished;
    private int archived;
    private int cleaned;
    private String tname;
    private int runing;
    private Integer tid;
    private int dateout;
    @ManyToMany( mappedBy="exams",fetch =FetchType.LAZY, targetEntity=Student.class)
    private List<Student> students ;


    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public int getAutostart() {
        return autostart;
    }

    public void setAutostart(int autostart) {
        this.autostart = autostart;
    }

    public String getExampaper() {
        return exampaper;
    }

    public void setExampaper(String exampaper) {
        this.exampaper = exampaper;
    }

    public int getStarted() {
        return started;
    }

    public void setStarted(int started) {
        this.started = started;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    public int getCleaned() {
        return cleaned;
    }

    public void setCleaned(int cleaned) {
        this.cleaned = cleaned;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getRuning() {
        return runing;
    }

    public void setRuning(int runing) {
        this.runing = runing;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getDateout() {
        return dateout;
    }

    public void setDateout(int dateout) {
        this.dateout = dateout;
    }
}
