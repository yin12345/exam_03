package edu.henu.exam_03.exam_03.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import edu.henu.exam_03.exam_03.dao.ExamDao;
import edu.henu.exam_03.exam_03.entity.Admin;
import edu.henu.exam_03.exam_03.entity.Exam;
import edu.henu.exam_03.exam_03.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class Scan implements SchedulingConfigurer {
	@Autowired
   private AdminService adminService;
	@Autowired
	private ExamDao examDao;
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		taskRegistrar.addTriggerTask(new Runnable() {

				@Override
				
				public void run() {
				
				// 定时任务的业务逻辑

				List<Exam> exams=examDao.findAll();
				Date date=new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
				
				for (Exam exam : exams) {
					
					Date date1 =exam.getStarttime();
					
					
					if (exam.getAutostart()==1 && date.before(date1) && exam.getExampaper()!=null ) {
						exam.setStarted(1);
						exam.setRuning(1);
						System.out.println(exam.getEname()+"11111111111111111111111111111111111111111111111111111111111111111111111111！");
						System.out.println(exam.getEname()+"正在考试！");
					}
				}
				examDao.saveAll(exams);
				}
				
				}, new Trigger() {
				
				@Override
				
				public Date nextExecutionTime(TriggerContext triggerContext) {
				Admin admin=adminService.findAaAdmin();
				String minuteString=admin.getDutycycle();
				String cron = "0 0/"+minuteString+" * * * ?";
				
			
				
				CronTrigger trigger = new CronTrigger(cron); // 定时任务触发，可修改定时任务的执行周期
				
				Date nextExecDate = trigger.nextExecutionTime(triggerContext);
				
				return nextExecDate;
				
				}
				
				});
					}

}
