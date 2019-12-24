package edu.henu.exam_03.exam_03.dao;

import edu.henu.exam_03.exam_03.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface TeacherDao extends JpaRepository<Teacher,Integer> {

    @Query("select u from Teacher u where u.name=?1 and u.pass=?2")
    Teacher login(String name,String pass);
    @Query("select u from Teacher u where u.id=?1")
    Teacher findTeacherById(Integer id);
    @Query("select u from Teacher u")
    Page<Teacher> findTeachersByPages(Pageable pageable);
}
