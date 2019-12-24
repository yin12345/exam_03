package edu.henu.exam_03.exam_03.dao;

import edu.henu.exam_03.exam_03.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student,String> {
    @Query("select u from Student u where u.stu_id=?1 and u.stu_name=?2")
    Student stu_login(String stu_id, String stu_name);

    @Query("select u from Student u where u.stu_id=?1 and u.stu_exam=?2")
    Student stu_history(String stu_id,String stu_exam);


    @Query("select u from Student u where u.stu_id=?1")
    Student findStudentByStu_id(String stu_id);

    @Query("select u from Student u where u.stu_ip=?1")
    Student findStudentByStu_ip(String stu_ip);



    @Query("select u from Student u inner join u.exams r where r.eid = ?1")
    Page<Student> findStudentsByExams(Integer eid,Pageable pageable);

    @Query("select u from Student u inner join u.exams r where r.eid = ?1")
    List<Student> findStudentsByeid(Integer eid);
    @Query("select u from Student u inner join u.exams r where u.stu_id=?1 and r.eid = ?2")
    Student findStudentByStu_idAndStu_exam(String stu_id,Integer eid);
    @Query("select count(u.stu_id) from Student u inner join u.exams r where r.eid = ?1")
   int findStudentsNum(Integer eid);
  //@Query("select u from Student u where u.stu_exam=?1")
  // List<Student> findStudentsByStu_exam(Integer stu_exam);
/*
    @Query("select u from Student u where u.stu_exam=?1")
    Page<Student> findStudentsByStu_exam(Integer stu_exam, Pageable pageable);

    @Query(value = "select count(u.stu_id) from Student u where u.stu_exam=?1")
    int findStudentNum (Integer stu_exam);
    */

}
