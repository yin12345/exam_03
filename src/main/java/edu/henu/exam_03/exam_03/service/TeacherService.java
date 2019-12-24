package edu.henu.exam_03.exam_03.service;


import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public interface TeacherService {
    String Login(String t_username, String t_pwd, HttpServletRequest request);
    List<Exam> addexam(Exam exam,HttpServletRequest request);
    Exam updateexam(Exam exam);
    Exam uploadpaper(Integer eid,String filename);
    Student addstudent(Student student,Integer eid);
   void delstudent(Student student,Integer eid);
    Page<Student> findstudent(Integer eid, int page,int size);
    int findStudentNum(Integer stu_exam);
    Exam findexam(Exam exam);
    Exam starteexam(Exam exam);
    void summary(Integer eid,HttpServletRequest request);
    List<Exam> finish(Exam exam,Integer tid);

    List<Exam> clean(Exam exam, Integer tid);

    Student search(Student student,Integer eid);

    Student searchip(Student student);

    Student unlock(Student student);

    Student selectstudent(Student student, Integer eid);

    List<Exam> archive(Exam exam, Integer tid);
    List<Exam> findexams( Integer tid);

    String fix(String name, String temp ,String pass2);
}
