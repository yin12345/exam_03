package edu.henu.exam_03.exam_03.dao;

import edu.henu.exam_03.exam_03.entity.Exam;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ExamDao extends JpaRepository<Exam,Integer>{

    Exam findExamByEid(Integer eid);
    @Query("select u from Exam u where u.ename=?1")
    Exam findExamByEname(String ename);

    @Query("select u from Exam u where u.tid=?1")
    List<Exam> findExamsByTid(Integer tid);


}
