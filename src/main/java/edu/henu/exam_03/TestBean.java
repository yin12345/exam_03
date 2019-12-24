package edu.henu.exam_03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class TestBean {
    @RequestMapping("/index")
    public String Test(){
        return "index";
    }
}
