package edu.henu.exam_03.exam_03.dao;

import edu.henu.exam_03.exam_03.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student,String> {
    @Query("select u from Student u where u.stu_id=?1")
    Student findStudentByStu_id(String stu_id);


  //@Query("select u from Student u where u.stu_exam=?1")
  // List<Student> findStudentsByStu_exam(Integer stu_exam);

    @Query("select u from Student u where u.stu_exam=?1")
    Page<Student> findStudentsByStu_exam(Integer stu_exam, Pageable pageable);

    @Query(value = "select coalesce(sum(u.stu_id),0 ) from Student u where u.stu_exam=?1")
    int findUserNum(Integer stu_exam);
}
