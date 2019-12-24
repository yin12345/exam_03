package edu.henu.exam_03.exam_03.controller;


import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.service.TeacherService;
import edu.henu.exam_03.exam_03.utils.Download;
import edu.henu.exam_03.exam_03.utils.ExcelUtils;


import edu.henu.exam_03.exam_03.utils.FileToZip;
import edu.henu.exam_03.exam_03.utils.Password;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Controller
@Configuration
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("fix")
    public String fix(@RequestParam("name") String name,@RequestParam("pass1") String pass1, @RequestParam("pass2") String pass2, HttpServletRequest request){
        String temp= Password.getMd5(Password.getMd5(pass1));

        System.out.println(temp);
        return teacherService.fix(name,temp,pass2);
    }
    @PostMapping("login")
    public String login(@RequestParam("name") String name, @RequestParam("pass") String pass, HttpServletRequest request){
        String temp= Password.getMd5(Password.getMd5(pass));

        System.out.println(temp);
        return teacherService.Login(name,temp,request);
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
        HttpSession session=request.getSession();
        session.setAttribute("exam",exam1);
        return "teacher_exam_modify";
    }
    @PostMapping("teacher_exam_upload")
    public String uploadpaper(Integer eid, MultipartFile paper, HttpServletRequest request) throws IOException {

        if(!paper.isEmpty()){
            //上传文件路径
            String path =request.getServletContext().getRealPath("/upload/");
            System.out.println("path="+path);
            //上传文件名
            String filename=paper.getOriginalFilename();
            // 文件上传时，Chrome和IE/Edge对于originalFilename处理不同
            // Chrome 会获取到该文件的直接文件名称，IE/Edge会获取到文件上传时完整路径/文件名
            // Check for Unix-style path
            int unixSep = filename.lastIndexOf('/');
            // Check for Windows-style path
            int winSep = filename.lastIndexOf('\\');
            // Cut off at latest possible point
            int pos = (winSep > unixSep ? winSep : unixSep);
            if (pos != -1)  {
                // Any sort of path separator found...
                filename = filename.substring(pos + 1);
            }
            File filepath =new File(path,filename);
            if(!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            paper.transferTo(new File(path+File.separator+filename));
           Exam exam= teacherService.uploadpaper(eid,filename);
            HttpSession session=request.getSession();
            session.setAttribute("exam",exam);
            return "teacher_exam_modify";
        }
        return "teacher_exam_modify";
    }
    @RequestMapping("download")
    public ResponseEntity<byte[]> download(String filename,HttpServletRequest request,@RequestHeader("User-Agent") String userAgent,Model model) throws Exception {
        //下载文件路径
        String path =request.getServletContext().getRealPath("/upload");
       return Download.downloadfile(filename,path,userAgent);


    }

    @PostMapping("addstudent")
    public String addstudent(Student student, HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int page = (int) session.getAttribute("page");
        int size = (int) session.getAttribute("size");
        teacherService.addstudent(student,eid);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        int totalPages=students.getTotalPages();
        session.setAttribute("totalPages",totalPages);
        List<Student> students1 = students.getContent();
        request.setAttribute("students",students1);
        int studentNum=teacherService.findStudentNum(eid);
        System.out.println(studentNum);
        session.setAttribute("studentNum",studentNum);
        return "teacher_student";
    }
    @PostMapping("insert")
    public String insertstudent(Student student, HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");

       Student student1= teacherService.addstudent(student,eid);
       request.setAttribute("entity",student1);
        return "teacher_manage_student";
    }
    @PostMapping("select")
    public String selectstudent(Student student, HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");

        Student student1= teacherService.selectstudent(student,eid);
        request.setAttribute("entity",student1);
        return "teacher_manage_student";
    }
    @GetMapping("delstudent")
    public String delstudent(Student student, HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int page = (int) session.getAttribute("page");
        int size = (int) session.getAttribute("size");
        teacherService.delstudent(student,eid);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        int totalPages=students.getTotalPages();
        session.setAttribute("totalPages",totalPages);
        List<Student> students1 = students.getContent();
        request.setAttribute("students",students1);
        int studentNum=teacherService.findStudentNum(eid);
        session.setAttribute("studentNum",studentNum);
        return "teacher_student";
    }
    @PostMapping("setsize")
    public String setsize(int size,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int page = (int) session.getAttribute("page");
        session.setAttribute("size",size);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        int totalPages=students.getTotalPages();
        session.setAttribute("totalPages",totalPages);
        List<Student> students1 = students.getContent();
        request.setAttribute("students",students1);
        return "teacher_student";
    }
    @RequestMapping(value = "setpage",method = {RequestMethod.GET,RequestMethod.POST})
    public String setpage(int page,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int size = (int) session.getAttribute("size");
        session.setAttribute("page",page);
        Page<Student> students =teacherService.findstudent(eid,page,size);
        int totalPages=students.getTotalPages();
        session.setAttribute("totalPages",totalPages);
        List<Student> students1 = students.getContent();
        request.setAttribute("students",students1);
        return "teacher_student";
    }

    @RequestMapping("uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file,
                              Map<String, Object> map,HttpServletRequest request) throws UnsupportedEncodingException {

        HttpSession session=request.getSession();
        Integer eid= (Integer) session.getAttribute("eid");
        int size = (int) session.getAttribute("size");
        int page = (int) session.getAttribute("page");
        String name = file.getOriginalFilename();
        // 文件上传时，Chrome和IE/Edge对于originalFilename处理不同
        // Chrome 会获取到该文件的直接文件名称，IE/Edge会获取到文件上传时完整路径/文件名
        // Check for Unix-style path
        int unixSep = name.lastIndexOf('/');
        // Check for Windows-style path
        int winSep = name.lastIndexOf('\\');
        // Cut off at latest possible point
        int pos = (winSep > unixSep ? winSep : unixSep);
        if (pos != -1)  {
            // Any sort of path separator found...
            name = name.substring(pos + 1);
        }
        if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
            return "文件格式错误";
        }
        List<Student> list = null;
        try {
            list = ExcelUtils.excelToShopIdList(file.getInputStream());
            if (list == null || list.size() <= 0) {
                return "导入的数据为空";
            }
            //excel的数据保存到数据库
            try {
                for (Student excel : list) {
                    excel.setStu_exam(eid);
                    teacherService.addstudent(excel,eid);


                }

                Page<Student> students =teacherService.findstudent(eid,page,size);

                int totalPages=students.getTotalPages();
                session.setAttribute("totalPages",totalPages);

                List<Student> students1 = students.getContent();
                request.setAttribute("students",students1);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "teacher_student";
    }
    @PostMapping("startexam")
    public String startexam(Exam exam,HttpServletRequest request){

        Exam exam1 =teacherService.starteexam(exam);

        HttpSession session=request.getSession();
        Integer tid= (Integer) session.getAttribute("id");
        List<Exam> exams =teacherService.findexams(tid);

        session.setAttribute("exam",exam1);
        session.setAttribute("exams",exams);
        return "teacher_exam_modify";
    }
    @PostMapping("finish")
    public String finish(Exam exam,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer tid= (Integer) session.getAttribute("id");
        List<Exam> exams =teacherService.finish(exam,tid);
        session.setAttribute("examname", null);
        session.setAttribute("eid",null);
        session.setAttribute("exams",exams);
        return "teacher_exam_after";
    }

    @RequestMapping("clean")
    public String clean(Exam exam,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer tid= (Integer) session.getAttribute("id");
        List<Exam> exams =teacherService.clean(exam,tid);

        session.setAttribute("exams",exams);
        return "teacher_exam_after";
    }
    @PostMapping("search")
    public String search(Student student,HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer eid = (Integer) session.getAttribute("eid");
       Student student1 =teacherService.search(student,eid);
        session.setAttribute("search",student1);
        return "teacher_manage_unlock";
    }
    @PostMapping("searchip")
    public String searchip(Student student,HttpServletRequest request){
        HttpSession session=request.getSession();
        Student student1 =teacherService.searchip(student);
        session.setAttribute("search",student1);
        return "teacher_manage_unlock";
    }
    @PostMapping("unlock")
    public String unlock(Student student,HttpServletRequest request){
        HttpSession session=request.getSession();
        Student student1 =teacherService.unlock(student);
        session.setAttribute("search",student1);
        return "teacher_manage_unlock";
    }
    @PostMapping("addmessage")
    public String addmessage(String message,HttpServletRequest request){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date1=simpleDateFormat.format(date);
        String message1=date1+": "+message;
        HttpSession session=request.getSession();
        List<String> messages1= (List<String>) session.getAttribute("messages");
        List<String> messages;
        if(messages1==null){
            messages=new ArrayList<>();
        }else {
            messages=messages1;
        }
        messages.add(message1);
        session.setAttribute("messages",messages);
        return "teacher_manage_notify";
    }
    @RequestMapping("delmessage")
    public String delmessage(String message,HttpServletRequest request){
        HttpSession session=request.getSession();
        List<String> messages= (List<String>) session.getAttribute("messages");

        messages.remove(message);

        session.setAttribute("messages",messages);
        return "teacher_manage_notify";
    }
    @RequestMapping("archive")
    public ResponseEntity<byte[]> archive(Exam exam,HttpServletRequest request,@RequestHeader("User-Agent") String userAgent,Model model) throws Exception {
        System.out.println(exam.getEname());
        //要压缩的文件路径
        String path =request.getServletContext().getRealPath("/upload1/"+exam.getEname());
        //生成Zip存放地址
        String path1=request.getServletContext().getRealPath("/download");
        String filename1=exam.getEname()+".zip";
        try {
            boolean flag = FileToZip.fileToZip(path,path1,exam.getEname());
            if(flag){
                System.out.println("打包成功");
            }else{
                System.out.println("打包失败");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        HttpSession session=request.getSession();
        Integer tid= (Integer) session.getAttribute("id");
        List<Exam> exams =teacherService.archive(exam,tid);
        session.setAttribute("exams",exams);

          return   Download.downloadfile(filename1,path1,userAgent) ;





    }

}

