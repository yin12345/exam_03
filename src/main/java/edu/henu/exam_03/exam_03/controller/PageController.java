package edu.henu.exam_03.exam_03.controller;

import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.dao.StudentDao;
import edu.henu.exam_03.exam_03.dao.TeacherDao;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String Test4(){
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
        HttpSession session=request.getSession();
        session.setAttribute("exam",exam);
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
        int size=30;
        session.setAttribute("page",page);
        session.setAttribute("size",size);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        int totalPages=students.getTotalPages();
        session.setAttribute("totalPages",totalPages);
        System.out.println(students);
        List<Student> students1 = students.getContent();
        request.setAttribute("students",students1);

        return "teacher_student";
    }

}

