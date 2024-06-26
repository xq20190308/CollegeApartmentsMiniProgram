package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.User;
import com.william.collegeapartmentsbacke.service.CoursemainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CoursemainService coursemainService;
    @RequestMapping("/obtainCourse")
    public String test(@RequestBody User user) {
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        if(coursemainService.initialization())
        {
            String []s=coursemainService.getCurrentTime().exec().split(",");
            String zc=s[0].substring(6,8);
            String week=s[3].substring(9,20);
            coursemainService.setCurtime(zc,week);
            return coursemainService.getTable().exec();
        }
        else
        {
            return "error";
        }

    }
    @RequestMapping("/Login")
    public Result loginInital(@RequestBody User user) {
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        if(coursemainService.initialization())
        {
            Map<String,String> account=new HashMap<>();
            account.put("password",user.getPassword());
            account.put("username",user.getUsername());
            return Result.success(account);
        }
        else
        {
            return Result.error("password invalid");
        }

    }
}
