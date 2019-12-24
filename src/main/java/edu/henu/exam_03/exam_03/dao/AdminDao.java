package edu.henu.exam_03.exam_03.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.henu.exam_03.exam_03.entity.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer> {
	
	@Query("select u from Admin u where u.id=?1 and u.pass=?2 ")
	Admin Login(Integer id, String pass);
	@Query("select u from Admin u where u.id=?1")
	Admin finAdminById(Integer id);
	
	
	
}
