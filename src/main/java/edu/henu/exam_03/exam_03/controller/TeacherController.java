package edu.henu.exam_03.exam_03.controller;

import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Configuration
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("login")
    public String login(@RequestParam("name") String name, @RequestParam("pass") String pass, HttpServletRequest request){

        return teacherService.Login(name,pass,request);
    }
    @PostMapping("addexam")
    public String addexam(Exam exam,HttpServletRequest request){

        List<Exam> exams =teacherService.addexam(exam,request);
        HttpSession session=request.getSession();
        session.setAttribute("exams",exams);
        return "teacher_exam_before";
    }

    @PostMapping("updateexam")
    public String updateexam(Exam exam,HttpServletRequest request){
        System.out.println(exam.getEid()+exam.getEname());
        Exam exam1= teacherService.updateexam(exam);
        request.setAttribute("exam",exam1);
        return "teacher_exam_modify";
    }
    @PostMapping("teacher_exam_upload")
    public String uploadpaper(Integer eid, MultipartFile paper, HttpServletRequest request) throws IOException {
        System.out.println(eid);
        if(!paper.isEmpty()){
            //上传文件路径
            String path =request.getServletContext().getRealPath("/upload/");
            System.out.println("path="+path);
            //上传文件名
            String filename=paper.getOriginalFilename();
            //判断路径是否存在，如果不存在就创建一个
            File filepath =new File(path,filename);
            if(!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            paper.transferTo(new File(path+File.separator+filename));
            teacherService.uploadpaper(eid,filename);
            return "teacher_exam_modify";
        }
        return "teacher_exam_modify";
    }
    @PostMapping("addstudent")
    public String addstudent(Student student, HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int page = (int) session.getAttribute("page");
        int size = (int) session.getAttribute("size");
        teacherService.addstudent(student);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        request.setAttribute("students",students);
        return "teacher_student";
    }
    @GetMapping("delstudent")
    public String delstudent(Student student, HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int page = (int) session.getAttribute("page");
        int size = (int) session.getAttribute("size");
        teacherService.delstudent(student);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        request.setAttribute("students",students);
        return "teacher_student";
    }
    @GetMapping("setsize")
    public String setsize(int size,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int page = (int) session.getAttribute("page");
        session.setAttribute("size",size);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        request.setAttribute("students",students);
        return "teacher_student";
    }
    @GetMapping("setpage")
    public String setpage(int page,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int size = (int) session.getAttribute("size");
        session.setAttribute("page",page);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        request.setAttribute("students",students);
        return "teacher_student";
    }
}
