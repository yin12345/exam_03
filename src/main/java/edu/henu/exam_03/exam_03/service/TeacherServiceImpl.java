package edu.henu.exam_03.exam_03.service;

import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.dao.StudentDao;
import edu.henu.exam_03.exam_03.dao.TeacherDao;
import edu.henu.exam_03.exam_03.entity.Admin;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.entity.Teacher;
import edu.henu.exam_03.exam_03.utils.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ExamDao examDao;
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AdminService adminService;
    @Override
    public String Login(String name, String pass, HttpServletRequest request) {
        Teacher teacher=null;
        try {
            teacher = teacherDao.login(name,pass);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(teacher==null){
            return "index";
        }
       else {
          HttpSession session= request.getSession();
          session.setAttribute("name",teacher.getName());
            session.setAttribute("id",teacher.getId());
            ServletContext application=request.getSession().getServletContext();
            Admin admin_init=adminService.findAaAdmin();
            application.setAttribute("pagecount",admin_init.getPagecount());
            application.setAttribute("dutycycle",admin_init.getDutycycle());
            application.setAttribute("time",admin_init.getTime());
            application.setAttribute("high",admin_init.getHigh());
            application.setAttribute("allowed",admin_init.getAllowed());
            return "teacher_main";
        }
    }

    public String fix(String name, String temp, String pass2) {
        Teacher teacher=null;
        try {
            teacher = teacherDao.login(name,temp);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(teacher==null){
            return "login";
        }
        else {
            String temp2= Password.getMd5(Password.getMd5(pass2));

            System.out.println(temp2);
            teacher.setPass(temp2);
            teacherDao.save(teacher);
            return "index";
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
    public Student addstudent(Student student,Integer eid) {
        Student student1=null;
        Optional<Exam> exam1=examDao.findById(eid);
        Exam exam=exam1.get();
        student1=studentDao.findStudentByStu_idAndStu_exam(student.getStu_id(),eid);
        Student student2=studentDao.findStudentByStu_id(student.getStu_id());
        if(student!=null&&student1==null){
            if(student2!=null){
                student=student2;
            }
            List<Exam> exams= student.getExams();

            if(exams==null){
                exams=new ArrayList<>();
            }
            exams.add(exam);
            student.setExams(exams);
            return studentDao.save(student);
        }

        return null;

    }

    @Override
    public void delstudent(Student student,Integer eid) {
        Optional<Exam> exam1=examDao.findById(eid);
        Exam exam=exam1.get();
        Student student1=studentDao.findStudentByStu_id(student.getStu_id());
        List<Exam> exams=student1.getExams();
        System.out.println("-----------");
        System.out.println(exams.size());
        exams.remove(exam);
        student1.setExams(exams);
        System.out.println(exams.size());
        studentDao.save(student1);
        if(exams.size()==0){
            System.out.println("11111");
            studentDao.deleteById(student.getStu_id());
        }





    }

    @Override
    public Page<Student> findstudent(Integer eid, int page,int size) {
        Pageable pageable=null;

        List<Sort.Order> list =new ArrayList<>();
        Sort.Order order=new Sort.Order(Sort.Direction.ASC,"stu_id");
        list.add(order);
        Sort sort =Sort.by(list);
        pageable = PageRequest.of(page,size,sort);
        return studentDao.findStudentsByExams(eid,pageable);
    }

    @Override
    public int findStudentNum(Integer stu_exam) {

        return studentDao.findStudentsNum(stu_exam);

    }

    @Override
    public Exam findexam(Exam exam) {
        Exam exam1= examDao.findExamByEid(exam.getEid());

        return exam1;
    }
    @Override
    public List<Exam> findexams(Integer tid) {


        return examDao.findExamsByTid(tid);
    }

    @Override
    public Exam starteexam(Exam exam)  {
        Date currentTime =new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date1=simpleDateFormat.format(currentTime);
        System.out.println(date1);
        Exam exam1=examDao.findExamByEid(exam.getEid());
        Date date2=exam1.getStarttime();
        try {

            Date date=simpleDateFormat.parse(date1);

            boolean bool = date.before(date2);
            System.out.println(bool);
            if(bool==true){
                exam1.setDateout(0);
                exam1.setStarted(exam.getStarted());
                exam1.setRuning(1);
                exam1=examDao.save(exam1);

            }else{


                exam1.setDateout(1);
                exam1=examDao.save(exam1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return exam1;

    }

    @Override
    public void summary(Integer eid,HttpServletRequest request) {
       List<Student> students= studentDao.findStudentsByeid(eid);

       int online=0;
       int unline;
       int submit=0;
       int unsubmit;
        for(Student student:students){
            if(student.getStu_ip()!=null){
                online++;
            }
            if(student.getStu_submit()!=null){
                submit++;
            }
        }
        HttpSession session=request.getSession();
        session.setAttribute("studentNum",students.size());
        session.setAttribute("online",online);
        int studentNum= (int) session.getAttribute("studentNum");
        unline=studentNum-online;
        session.setAttribute("unline",unline);
        session.setAttribute("submit",submit);
        unsubmit=studentNum-submit;
        session.setAttribute("unsubmit",unsubmit);
    }

    @Override
    public List<Exam> finish(Exam exam,Integer tid) {

        Exam exam1=examDao.findExamByEid(exam.getEid());
        exam1.setFinished(1);
        exam1.setRuning(0);
        examDao.save(exam1);

        return examDao.findExamsByTid(tid);
    }

    @Override
    public List<Exam> clean(Exam exam, Integer tid) {
        Exam exam1=examDao.findExamByEid(exam.getEid());
       List<Student> students=studentDao.findStudentsByeid(exam1.getEid());
       for(Student student:students){
            List<Exam> exams =student.getExams();
            exams.remove(exam1);
           student.setExams(exams);
           studentDao.save(student);
           if(exams.size()==0){
               System.out.println("11111");
               studentDao.deleteById(student.getStu_id());
           }

       }
        examDao.delete(exam1);

        return examDao.findExamsByTid(tid);
    }

    @Override
    public Student search(Student student,Integer eid) {


        try {
            Student student1=studentDao.findStudentByStu_idAndStu_exam(student.getStu_id(),eid);
            if(student1.getStu_ip()!=null){
                return  student1;
            }else {
                System.out.println("-----------b");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Student searchip(Student student) {
        try {
            Student student1= studentDao.findStudentByStu_ip(student.getStu_ip());
            return student1;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Student unlock(Student student) {
        Student student1=studentDao.findStudentByStu_id(student.getStu_id());
        student.setStu_id(student1.getStu_id());
        student.setStu_name(student1.getStu_name());
        student.setStu_class(student1.getStu_class());
        student.setStu_submit(student1.getStu_submit());
        student.setExams(student1.getExams());
        return studentDao.save(student);
    }

    @Override
    public Student selectstudent(Student student, Integer eid) {
        try {
            return studentDao.findStudentByStu_idAndStu_exam(student.getStu_id(),eid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Exam> archive(Exam exam, Integer tid) {
        Exam exam1=examDao.findExamByEid(exam.getEid());
        exam1.setArchived(1);
        examDao.save(exam1);
        return examDao.findExamsByTid(tid);
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
    public Exam uploadpaper(Integer eid, String filename) {
        Exam exam=examDao.findExamByEid(eid);
        if(exam!=null){
            exam.setExampaper(filename);
           return examDao.save(exam);
        }
        return exam;
    }




}
