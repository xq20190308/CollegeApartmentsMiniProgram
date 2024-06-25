package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.service.CoursemainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CoursemainService coursemainService;
    @RequestMapping("/obtainCourse")
    public String test() {
        coursemainService.initialization();
        String []s=coursemainService.getCurrentTime().exec().split(",");
        String zc=s[0].substring(6,8);
        String week=s[3].substring(9,20);
        coursemainService.setCurtime(zc,week);
        return coursemainService.getTable().exec();
    }
}
