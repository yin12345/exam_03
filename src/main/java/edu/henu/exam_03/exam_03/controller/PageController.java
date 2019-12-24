package edu.henu.exam_03.exam_03.controller;

import edu.henu.exam_03.exam_03.dao.AdminDao;
import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.dao.StudentDao;
import edu.henu.exam_03.exam_03.dao.TeacherDao;
import edu.henu.exam_03.exam_03.entity.Admin;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.entity.Teacher;
import edu.henu.exam_03.exam_03.service.AdminService;
import edu.henu.exam_03.exam_03.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Configuration

public class PageController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ExamDao examDao;
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AdminService adminService;
    @RequestMapping("/before")
    public String Test1(HttpServletRequest request){

        HttpSession session=request.getSession();
        Integer tid= (Integer) session.getAttribute("id");
        List<Exam> exams =examDao.findExamsByTid(tid);
        session.setAttribute("exams",exams);
        return "teacher_exam_before";
    }
    @RequestMapping("/after")
    public String Test2(){
        return "teacher_exam_after";
    }
    @RequestMapping("/main")
    public String Test3(){
        return "teacher_main";
    }

    @RequestMapping("/teacher_manage_summary")
    public String Test4(HttpServletRequest request){

        HttpSession session=request.getSession();
        Integer eid = (Integer) session.getAttribute("eid");
        teacherService.summary(eid,request);

        return "teacher_manage_summary";
    }
    @RequestMapping("/teacher_manage_student")
    public String Test5(){
        return "teacher_manage_student";
    }
    @RequestMapping("/teacher_manage_unlock")
    public String Test6(){
        return "teacher_manage_unlock";
    }
    @RequestMapping("/teacher_manage_notify")
    public String Test7(){
        return "teacher_manage_notify";
    }

    @RequestMapping("/teacher_exam_modify")
    public String Test8(Exam exam,HttpServletRequest request){
       int studentNum= teacherService.findStudentNum(exam.getEid());

       Exam exam1=teacherService.findexam(exam);
       System.out.println(studentNum);
        HttpSession session=request.getSession();
        session.setAttribute("exam",exam1);
        session.setAttribute("studentNum",studentNum);
        return "teacher_exam_modify";
    }
    @RequestMapping("/teacher_exam_modify1")
    public String Test8_1(){

        return "teacher_exam_modify";
    }
    @RequestMapping("/teacher_student")
    public String Test9(Integer eid,HttpServletRequest request){
        System.out.println(eid);
        HttpSession session=request.getSession();
        session.setAttribute("eid",eid);
        int page=0;
        Admin admin=adminService.findAaAdmin();
        int size=Integer.parseInt(admin.getPagecount());
        session.setAttribute("page",page);
        session.setAttribute("size",size);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        int totalPages=students.getTotalPages();
        session.setAttribute("totalPages",totalPages);

        List<Student> students1 = students.getContent();
        request.setAttribute("students",students1);

        return "teacher_student";
    }

    @RequestMapping("admin_main")
    public String Toadmin_Main() {
        return"admin_main";
    }
    @RequestMapping("/admin_clear_jsp")
    public String Test10(HttpServletRequest request) {

        HttpSession session=request.getSession();
        ServletContext application=request.getSession().getServletContext();
        Page<Exam> exams=adminService.findExam(0, Integer.valueOf((String)application.getAttribute("pagecount")));
        List<Exam> myExams=exams.getContent();
        session.setAttribute("exams",myExams);
        session.setAttribute("current_page",0);
        return"admin_clear";
    }

    @RequestMapping("/admin_manage_jsp")
    public String Test11(HttpServletRequest request) {
        HttpSession session=request.getSession();
        ServletContext application=request.getSession().getServletContext();
        Page<Teacher> teachers=adminService.findTeacher(0,Integer.valueOf((String)application.getAttribute("pagecount")));
        List<Teacher> myteachers=teachers.getContent();

        session.setAttribute("current_page",0);
        session.setAttribute("teachers",myteachers);
        return"admin_manage";
    }
    @RequestMapping("/admin_conf_jsp")
    public String Test12(HttpServletRequest request) {

        return"admin_conf";
    }
    @RequestMapping("/admin_revise_teacher_jsp")
    public String Test13(HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.setAttribute("revise_teacher_id", request.getParameter("id"));
        return"admin_revise_teacher";
    }

    @RequestMapping("/admin_login")
    public String Test14(HttpServletRequest request) {

        return"admin_login";
    }
    @RequestMapping("/fix1")
    public String Test15(HttpServletRequest request) {

        return"login";
    }
    @RequestMapping("/admin_fix")
    public String Test16(HttpServletRequest request) {

        return"admin_fix";
    }
}

