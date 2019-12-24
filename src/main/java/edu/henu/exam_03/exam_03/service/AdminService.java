package edu.henu.exam_03.exam_03.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import edu.henu.exam_03.exam_03.entity.Admin;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.entity.Teacher;



public interface AdminService {
   String Login(Integer user, String pass, HttpServletRequest request);
   void Clear_Exam(Integer eid);
   void Delete_Exam(Integer eid);
   void Add_teacher(Teacher teacher);
   void Delete_teacher(Integer id);
   void Delete_admin(Integer id);
   void Update_teacher(Integer oldid, Integer newid, String name, String pass, String admin);
   void System_Conf(HttpServletRequest request);
   Page<Exam> findExam(int page, int size);
   int total_amount_of_exam();
   int total_amount_of_teacher();
   Page<Teacher> findTeacher(int page, int size);
   Admin findAaAdmin();

    void Admin_reviese_pass(HttpServletRequest request);
}
