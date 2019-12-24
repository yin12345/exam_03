package edu.henu.exam_03.exam_03.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.henu.exam_03.exam_03.utils.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.henu.exam_03.exam_03.dao.AdminDao;
import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.dao.StudentDao;
import edu.henu.exam_03.exam_03.dao.TeacherDao;
import edu.henu.exam_03.exam_03.entity.Admin;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Student;
import edu.henu.exam_03.exam_03.entity.Teacher;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao admindao;
	@Autowired
	private ExamDao examDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;
	@Override
	public String Login(Integer id, String pass, HttpServletRequest request) {
		Admin admin = null;
		System.out.println("AdminService界面的id=" + id + "pass=" + pass);
		admin = admindao.Login(id, pass);
		if (admin == null) {
			return "admin_login";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("name", admin.getName());
			session.setAttribute("id", admin.getId());
			ServletContext application=request.getSession().getServletContext();
			Admin admin_init=findAaAdmin();
			application.setAttribute("pagecount",admin_init.getPagecount());
			application.setAttribute("dutycycle",admin_init.getDutycycle());
			application.setAttribute("time",admin_init.getTime());
			application.setAttribute("high",admin_init.getHigh());
			application.setAttribute("allowed",admin_init.getAllowed());
			return "admin_main";
		}

	}

	@Override
	public void Clear_Exam(Integer eid) {
		// TODO Auto-generated method stub
		Exam exam = null;
		exam = examDao.findExamByEid(eid);
		if (exam == null) {
			System.out.println("没有找到考试信息！");
		} else {
			System.out.println("找到了考试信息！");
			exam.setExampaper(null);
			exam.setCleaned(1);
			System.out.println("cleaned=" + exam.getCleaned());
			exam.setRuning(0);
			exam.setTid(null);
			exam.setTname(null);
			examDao.save(exam);
			List<Student> student = null;
			student = studentDao.findStudentsByeid(eid);
			for (Student student2 : student) {
				studentDao.delete(student2);
			}
		}
	}

	@Override
	public void Delete_Exam(Integer eid) {
		// TODO Auto-generated method stub
		Exam exam=examDao.findExamByEid(eid);



		examDao.delete(exam);
	}


	@Override
	public void Add_teacher(Teacher teacher) {
		// TODO Auto-generated method stub
		Teacher teacher2=teacherDao.findTeacherById(teacher.getId());
		if(teacher2==null&&teacher!=null) {
			teacherDao.save(teacher);
			if (teacher.getAdmin().toString().equals("true")) {
				Admin admin3=new Admin();
				System.out.println("teacher.getAdmin()"+teacher.getAdmin());
				admin3.setId(teacher.getId());
				admin3.setName(teacher.getName());
				admin3.setPass(teacher.getPass());
				System.out.println("old pass is "+teacher.getPass());

				Admin admin0 = findAaAdmin();
				admin3.setPagecount(admin0.getPagecount());
				admin3.setTime(admin0.getTime());
				admin3.setHigh(admin0.getHigh());
				admin3.setDutycycle(admin0.getDutycycle());
				admin3.setAllowed(admin0.getAllowed());

				admindao.save(admin3);
			}
		}

	}




	@Override
	public void Delete_teacher(Integer id) {
		// TODO Auto-generated method stub
		Teacher teacher=teacherDao.findTeacherById(id);
		teacherDao.delete(teacher);
	}

	@Override
	public void Delete_admin(Integer id) {
		// TODO Auto-generated method stub
		Admin admin=admindao.finAdminById(id);
		if(admin!=null)admindao.delete(admin);
	}

	@Override
	public void Update_teacher(Integer oldid, Integer newid, String name, String pass, String admin) {
		// TODO Auto-generated method stub
		Teacher teacher=teacherDao.findTeacherById(oldid);
		teacher.setId(newid);
		teacher.setName(name);
		if(pass!="") {
			teacher.setPass(pass);
		}
		teacher.setAdmin(admin);
		teacherDao.save(teacher);
		Admin admin2=admindao.finAdminById(oldid);
		if(admin2!=null)
		{
			if(teacher.getAdmin().toString().equals("true")) {
				admin2.setId(newid);
				admin2.setName(name);
				if (pass!="") {
					admin2.setPass(pass);
				}
				admindao.save(admin2);
			}
			else {
				admindao.delete(admin2);
			}

		}
		else {
			if (admin.equals("true")) {
				System.out.println("newid是"+newid);
				Admin admin3 = new Admin();
				admin3.setId(newid);
				admin3.setName(name);

				Admin admin0 = findAaAdmin();
				admin3.setPagecount(admin0.getPagecount());
				admin3.setTime(admin0.getTime());
				admin3.setHigh(admin0.getHigh());
				admin3.setDutycycle(admin0.getDutycycle());
				admin3.setAllowed(admin0.getAllowed());

				if(pass!="") {
					admin3.setPass(pass);
				}
				if(pass=="") {
					admin3.setPass(teacher.getPass());
					System.out.println("密码是："+teacher.getPass());
				}


				admindao.save(admin3);
			}
			else {
				Admin admin4 = new Admin();
				admin4.setId(newid);
				admin4.setName(name);
				if(pass!="") {
					admin4.setPass(pass);
				}
				if(pass=="") {
					admin4.setPass(teacher.getPass());
					System.out.println("密码是："+teacher.getPass());

				}
				Admin admin0 = findAaAdmin();
				admin4.setPagecount(admin0.getPagecount());
				admin4.setTime(admin0.getTime());
				admin4.setHigh(admin0.getHigh());
				admin4.setDutycycle(admin0.getDutycycle());
				admin4.setAllowed(admin0.getAllowed());


				admindao.save(admin4);
			}
		}
	}

	@Override
	public void System_Conf(HttpServletRequest request) {
		// TODO Auto-generated method stub


		List<Admin> admins=admindao.findAll();
		for (Admin admin2 : admins) {
			admin2.setDutycycle(request.getParameter("dutycycle"));
			admin2.setPagecount(request.getParameter("pagecount"));
			admin2.setTime(request.getParameter("time"));
			admin2.setHigh(request.getParameter("high"));
			admin2.setAllowed(request.getParameter("allowed"));
		}
		admindao.saveAll(admins);
		ServletContext application=request.getSession().getServletContext();
		application.setAttribute("pagecount",request.getParameter("pagecount"));
		application.setAttribute("dutycycle",request.getParameter("dutycycle"));
		application.setAttribute("time",request.getParameter("time"));
		application.setAttribute("high",request.getParameter("high"));
		application.setAttribute("allowed",request.getParameter("allowed"));
	}

	@Override
	public Page<Exam> findExam(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pageable=null;
		List<Sort.Order> list =new ArrayList<>();
		Sort.Order order=new Sort.Order(Sort.Direction.ASC,"eid");
		list.add(order);
		Sort sort =Sort.by(list);
		pageable = PageRequest.of(page,size,sort);
		return examDao.findExamsByPages(pageable);

	}

	@Override
	public int total_amount_of_exam() {
		// TODO Auto-generated method stub
		List<Exam>exams=examDao.findAll();
		return exams.size();
	}

	@Override
	public Page<Teacher> findTeacher(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pageable=null;
		List<Sort.Order> list =new ArrayList<>();
		Sort.Order order=new Sort.Order(Sort.Direction.ASC,"id");
		list.add(order);
		Sort sort =Sort.by(list);
		pageable = PageRequest.of(page,size,sort);
		return teacherDao.findTeachersByPages(pageable);

	}

	@Override
	public int total_amount_of_teacher() {
		List<Teacher> teachers=teacherDao.findAll();
		return teachers.size();
	}

	@Override
	public Admin findAaAdmin() {
		// TODO Auto-generated method stub
		List<Admin> list=admindao.findAll();
		return list.get(0);
	}

	@Override
	public void Admin_reviese_pass(HttpServletRequest request) {
		HttpSession session=request.getSession();
		int id=(int) session.getAttribute("id");
		Admin admin6=admindao.finAdminById(id);
		String tempString=(String)request.getParameter("pass2");
		admin6.setPass(Password.getMd5(Password.getMd5(tempString)));
		System.out.println("修改的管理员密码是："+request.getParameter("password"));
		admindao.save(admin6);
	}


}
