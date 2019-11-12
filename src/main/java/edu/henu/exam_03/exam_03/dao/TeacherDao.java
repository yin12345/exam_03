package edu.henu.exam_03.exam_03.dao;

import edu.henu.exam_03.exam_03.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface TeacherDao extends JpaRepository<Teacher,Integer> {

    @Query("select u from Teacher u where u.name=?1 and u.pass=?2")
    Teacher login(String name,String pass);
}
