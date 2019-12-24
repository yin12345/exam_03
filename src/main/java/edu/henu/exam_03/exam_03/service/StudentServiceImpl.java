package edu.henu.exam_03.exam_03.service;

import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.dao.StudentDao;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ExamDao examDao;

    @Override
    public String stu_login(String stu_id, String stu_name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Student student = null;
        Student student1=null;

        student = studentDao.stu_login(stu_id, stu_name);
        if (student == null) {
            return "student_login";
        }//学号姓名正确
        if(session.getAttribute("eid")==null){
            return "student_login";
        }//有正在进行的考试
        String ip = request.getHeader("x-forwarded-for");

        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        boolean can = false;




        if (ip.equals(student.getStu_ip())) {
            can = true;
        }
        if (student.getStu_ip() == null&&can==false) {

                student1=studentDao.findStudentByStu_ip(ip);


            if(student1!=null){
                can=false;
            }else {
                can=true;
                student.setStu_ip(ip);//绑定ip
                studentDao.save(student);
            }

        }
        if (can) {

            session.setAttribute("stu_id", stu_id);
            session.setAttribute("stu_name", stu_name);
            return "student_main";
        } else {
            return "student_login";
        }
    }

    @Override
    public String stu_answer_upload(Integer eid, MultipartFile paper,HttpServletRequest request) {

        return "student_main";
    }

    @Override
    public String stu_paper_download(HttpServletRequest request) {
        return "null";
    }
}