package edu.henu.exam_03.exam_03.controller;

import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.dao.StudentDao;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.service.StudentService;
import edu.henu.exam_03.exam_03.utils.Download;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller
@Configuration
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ExamDao examDao;


    @RequestMapping("/student_login")
    public String student_login() {
        return "student_login";
    }

    @RequestMapping("/stu_login")
    public String stu_login(@RequestParam("stu_id") String stu_id,
                            @RequestParam("stu_name") String stu_name,
                            HttpServletRequest request) {
        return studentService.stu_login(stu_id, stu_name, request);
    }

    @RequestMapping("/on_load_action")
    public String on_load_action(HttpServletRequest request){
        HttpSession session=request.getSession();

        List<Exam> exams=examDao.findAll();
        String examname="";
        for(Exam exam:exams)
        {
            if(exam.getStarted()==1)
            {
                examname=exam.getEname();
                session.setAttribute("examname", examname);
                session.setAttribute("eid",exam.getEid());
            }
            session.setAttribute("features",1);
        }
        return "student_login";
    }

    @RequestMapping("/stu_download")
    public String stu_download() {
        return "student_download";
    }

    @RequestMapping("/stu_upload")
    public String stu_upload() {
        return "student_upload";
    }

    @RequestMapping("/stu_history")
    public String stu_history() {
        return "student_history";
    }
    @RequestMapping("/stu_main")
    public String stu_main(){
        return "student_main";
    }

    @RequestMapping("/stu_paper_download")
    public ResponseEntity<byte[]> stu_paper_download(HttpServletRequest request, HttpServletResponse response, @RequestHeader("User-Agent") String userAgent) throws Exception {
        HttpSession session=request.getSession();

        Integer eid=(Integer) session.getAttribute("eid");
        Exam exam=null;
        exam=examDao.findExamByEid(eid);
        String examname=exam.getExampaper();
        String path =request.getServletContext().getRealPath("/upload");
        return Download.downloadfile(examname,path,userAgent);

    }

    @PostMapping("/stu_answer_upload")
    public String stu_answer_upload(@RequestParam(name="file") MultipartFile paper,
                                    HttpServletRequest request) throws IOException {
        HttpSession session=request.getSession();
        Integer eid=(Integer) session.getAttribute("eid");
        String stu_id=(String)session.getAttribute("stu_id");
        Exam exam=null;
        exam=examDao.findExamByEid(eid);
        Student student=null;
        student=studentDao.findStudentByStu_id(stu_id);
        String examname=exam.getEname();
        if (!paper.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/upload1/" +examname+"/");
            //上传文件名
            String filename = paper.getOriginalFilename();
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
            //判断路径是否存在，如果不存在就创建一个
            File filepath = new File(path, filename);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            try {
                paper.transferTo(new File(path + File.separator + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            student.setStu_submit("1");
            studentDao.save(student);
        }
        return "student_main";
    }
    @RequestMapping("/stu_file_history")
    public String stu_file_history(HttpServletRequest request, Model model){

        Student student=null;
        HttpSession session=request.getSession();
        String stu_id=(String)session.getAttribute("stu_id");
        student=studentDao.findStudentByStu_id(stu_id);
        String stu_submit=student.getStu_submit();
        List<Exam> exams=examDao.Stu_Submit(stu_id,stu_submit);
        StringBuilder sb=new StringBuilder();
        for (Exam exam:exams)
        {
            sb.append("<tr><td>");
            sb.append(exam.getEname());
            sb.append("</td><td>");
            sb.append(exam.getStarttime());
            sb.append("</td><td>");
            sb.append(exam.getTname());
            sb.append("</td><tr>");
        }
        model.addAttribute("table",sb);
        return "student_history";
    }
}
