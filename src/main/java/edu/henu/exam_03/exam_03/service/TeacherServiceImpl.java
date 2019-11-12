package edu.henu.exam_03.exam_03.service;

import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.dao.StudentDao;
import edu.henu.exam_03.exam_03.dao.TeacherDao;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ExamDao examDao;
    @Autowired
    private StudentDao studentDao;
    @Override
    public String Login(String name, String pass, HttpServletRequest request) {
        Teacher teacher=null;
        teacher = teacherDao.login(name,pass);
        if(teacher==null){
            return "index";
        }
       else {
          HttpSession session= request.getSession();
          session.setAttribute("name",teacher.getName());
            session.setAttribute("id",teacher.getId());
            return "teacher_main";
        }
    }

    @Override
    public List<Exam> addexam(Exam exam,HttpServletRequest request) {
        Exam exam1=null;
        HttpSession session =request.getSession();
        String tname= (String) session.getAttribute("name");
        Integer tid= (Integer) session.getAttribute("id");
        exam.setTname(tname);
        exam.setTid(tid);
        exam1=examDao.findExamByEname(exam.getEname());
        if(exam!=null&&exam1==null){
            examDao.save(exam);
        }

        return examDao.findExamsByTid(tid);

    }
    @Override
    public void addstudent(Student student) {
        Student student1=null;

        student1=studentDao.findStudentByStu_id(student.getStu_id());
        if(student!=null&&student1==null){
            studentDao.save(student);
        }



    }

    @Override
    public void delstudent(Student student) {
        studentDao.deleteById(student.getStu_id());

    }

    @Override
    public Page<Student> findstudent(Integer eid, int page,int size) {
        Pageable pageable=null;

        List<Sort.Order> list =new ArrayList<>();
        Sort.Order order=new Sort.Order(Sort.Direction.ASC,"stu_id");
        list.add(order);
        Sort sort =Sort.by(list);
        pageable = PageRequest.of(page,size,sort);
        return studentDao.findStudentsByStu_exam(eid,pageable);
    }

    @Override
    public Exam updateexam(Exam exam) {
        Exam exam1=examDao.findExamByEid(exam.getEid());


            exam1.setEname(exam.getEname());
            exam1.setStarttime(exam.getStarttime());
            exam1.setAutostart(exam.getAutostart());

            return  examDao.save(exam1);

    }

    @Override
    public void uploadpaper(Integer eid, String filename) {
        Exam exam=examDao.findExamByEid(eid);
        if(exam!=null){
            exam.setExampaper(filename);
            examDao.save(exam);
        }
    }




}
