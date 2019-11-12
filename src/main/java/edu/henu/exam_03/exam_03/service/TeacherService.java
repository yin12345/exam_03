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
    void uploadpaper(Integer eid,String filename);
    void addstudent(Student student);
   void delstudent(Student student);
    Page<Student> findstudent(Integer eid, int page,int size);
}
