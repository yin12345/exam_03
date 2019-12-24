package edu.henu.exam_03.exam_03.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.henu.exam_03.exam_03.utils.Password;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.entity.Teacher;
import edu.henu.exam_03.exam_03.service.AdminService;

@Controller
@Configuration
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/admin_revise_pass_reaction")
	public String test22(HttpServletRequest request) {
		adminService.Admin_reviese_pass(request);

		return"admin_login";
	}

	@PostMapping("admin_main")
	public String login(@RequestParam("id") Integer id, @RequestParam("pass") String pass, HttpServletRequest request){
		if (adminService.findAaAdmin()==null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", "admin");

			ServletContext application=request.getSession().getServletContext();

			application.setAttribute("pagecount",9);
			application.setAttribute("dutycycle",1);
			application.setAttribute("time",1);
			application.setAttribute("high",1);
			application.setAttribute("allowed",1);
			return "admin_main";
		}

		//--------
		System.out.println("[]"+Password.getMd5(pass));
		String temp=Password.getMd5(Password.getMd5(pass));
		//=======
		System.out.println("id="+id+"  pass="+temp);
		return adminService.Login(id,temp,request);


	}


	@GetMapping("admin_clear_reaction")
	public void toclear(@RequestParam("eid") Integer eid,HttpServletResponse response) throws IOException {
		adminService.Clear_Exam(eid);
		response.sendRedirect("admin_clear_jsp");

	}
	@GetMapping("admin_delete_reaction")
	public void todelete(@RequestParam("eid") Integer eid,HttpServletResponse response) throws IOException {
		adminService.Delete_Exam(eid);
		response.sendRedirect("admin_clear_jsp");

	}
	@PostMapping("admin_add_teacher")
	public void admin_add_teacher(Teacher teacher,HttpServletResponse response) throws IOException {
		//---------
		String str=teacher.getPass();
		String passString=Password.getMd5(Password.getMd5(str));
		teacher.setPass(passString);
		//==========
		adminService.Add_teacher(teacher);

		response.sendRedirect("admin_manage_jsp");
	}
	@GetMapping("admin_delete_teacher")
	public void delete_teacher(@RequestParam("id") Integer id,HttpServletResponse response) throws IOException {
		adminService.Delete_teacher(id);
		adminService.Delete_admin(id);
		response.sendRedirect("admin_manage_jsp");

	}
	@PostMapping("admin_revise_reaction")
	public void update_teacher(@RequestParam("id")Integer id,@RequestParam("name")String name,@RequestParam("pass")String pass,@RequestParam("admin")String admin,HttpServletResponse response,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession();
		Integer oldId=Integer.valueOf((String)session.getAttribute("revise_teacher_id"));
		//------------
		String temp=Password.getMd5(Password.getMd5(pass));
		adminService.Update_teacher(oldId, id, name, temp, admin);
		//===========

		System.out.println("new pass is "+pass);
		response.sendRedirect("admin_manage_jsp");
	}
	@PostMapping("admin_conf_reaction")
	public void admin_conf(HttpServletResponse response,HttpServletRequest request) throws IOException {

		adminService.System_Conf(request);
		response.sendRedirect("admin_main");
	}
	@PostMapping("admin_exam_setpage")
	public String admin_exam_setpage(@RequestParam("page")Integer page,HttpServletRequest request) throws IOException{
		System.out.println("page的值是"+page);
		HttpSession session=request.getSession();
		ServletContext application=request.getSession().getServletContext();
		if(page<0) {
			page=0;
		}

		session.setAttribute("current_page",page);
		Page<Exam> exams=adminService.findExam((Integer)session.getAttribute("current_page"),Integer.valueOf((String)application.getAttribute("pagecount")) );
		List<Exam> myExams=exams.getContent();
		session.setAttribute("exams",myExams);
		return"admin_clear";

	}
	@GetMapping("admin_exam_setpage")
	public String admin_exam_setpage_0(@RequestParam("page")Integer page,HttpServletRequest request) throws IOException{

		HttpSession session=request.getSession();
		ServletContext application=request.getSession().getServletContext();
		int pagecount=Integer.valueOf((String)application.getAttribute("pagecount"));
		if (page==-1) {
			page=0;
		}
		if (page==-2) {

			int num=adminService.total_amount_of_exam();
			int zhengshu=num/pagecount;
			int yushu=num-zhengshu*pagecount;
			System.out.println("整数："+zhengshu+"余数："+yushu);
			if (yushu==0) {
				session.setAttribute("current_page",zhengshu-1);
				Page<Exam> exams=adminService.findExam((Integer)session.getAttribute("current_page"),pagecount );
				List<Exam> myExams=exams.getContent();
				session.setAttribute("exams",myExams);
				return"admin_clear";
			}
			if (yushu!=0) {
				session.setAttribute("current_page",zhengshu);
				Page<Exam> exams=adminService.findExam((Integer)session.getAttribute("current_page"),pagecount );
				List<Exam> myExams=exams.getContent();
				session.setAttribute("exams",myExams);
				System.out.println("分页查询666");
				return"admin_clear";
			}

		}
		session.setAttribute("current_page",page);

		Page<Exam> exams=adminService.findExam((Integer)session.getAttribute("current_page"), Integer.valueOf((String)application.getAttribute("pagecount")));
		List<Exam> myExams=exams.getContent();
		session.setAttribute("exams",myExams);
		return"admin_clear";

	}

	@PostMapping("admin_teacher_setpage")
	public String admin_teacher_setpage(@RequestParam("page")Integer page,HttpServletRequest request) throws IOException{
		HttpSession session=request.getSession();
		ServletContext application=request.getSession().getServletContext();
		if(page<0) {
			page=0;
		}

		session.setAttribute("current_page",page);
		Page<Teacher> teachers=adminService.findTeacher((Integer)session.getAttribute("current_page"),Integer.valueOf((String)application.getAttribute("pagecount")) );
		List<Teacher> myteachers=teachers.getContent();
		session.setAttribute("teachers",myteachers);
		return"admin_manage";

	}
	@GetMapping("admin_teacher_setpage")
	public String admin_teacher_setpage_0(@RequestParam("page")Integer page,HttpServletRequest request) throws IOException{
		System.out.println("admin_teacher_setpage");
		HttpSession session=request.getSession();
		ServletContext application=request.getSession().getServletContext();
		int pagecount=Integer.valueOf((String)application.getAttribute("pagecount"));
		if (page==-1) {
			page=0;
		}
		if (page==-2) {

			int num=adminService.total_amount_of_teacher();
			int zhengshu=num/pagecount;
			int yushu=num-zhengshu*pagecount;
			System.out.println("整数："+zhengshu+"余数："+yushu);
			if (yushu==0) {
				session.setAttribute("current_page",zhengshu-1);
				Page<Teacher> teachers=adminService.findTeacher((Integer)session.getAttribute("current_page"),Integer.valueOf((String)application.getAttribute("pagecount")) );
				List<Teacher> myteachers=teachers.getContent();
				session.setAttribute("teachers",myteachers);
				return"admin_manage";
			}
			if (yushu!=0) {
				session.setAttribute("current_page",zhengshu);
				Page<Teacher> teachers=adminService.findTeacher((Integer)session.getAttribute("current_page"),Integer.valueOf((String)application.getAttribute("pagecount")) );
				List<Teacher> myteachers=teachers.getContent();
				session.setAttribute("teachers",myteachers);
				return"admin_manage";
			}

		}
		session.setAttribute("current_page",page);

		Page<Teacher> teachers=adminService.findTeacher((Integer)session.getAttribute("current_page"),Integer.valueOf((String)application.getAttribute("pagecount")) );
		List<Teacher> myteachers=teachers.getContent();
		session.setAttribute("teachers",myteachers);
		return"admin_manage";

	}
}
