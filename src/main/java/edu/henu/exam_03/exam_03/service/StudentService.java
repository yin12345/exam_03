package edu.henu.exam_03.exam_03.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface StudentService {
    String stu_login(String stu_id, String stu_name, HttpServletRequest request);
    String stu_answer_upload(Integer eid, MultipartFile paper, HttpServletRequest request);
    String stu_paper_download(HttpServletRequest request);


}
