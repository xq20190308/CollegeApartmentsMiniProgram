package com.william.collegeapartmentsbacke.controller;

import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.pojo.entity.Course;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.User;
import com.william.collegeapartmentsbacke.service.CoursemainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CoursemainService coursemainService;
    Course [][]Resultcourse=new Course[5][7];
    @RequestMapping ("/obtainCourse")
    public Result test(@RequestBody User user) {
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        for (int i = 0; i < Resultcourse.length; i++) {
            for (int j = 0; j < Resultcourse[i].length; j++) {
                Resultcourse[i][j] = new Course("0","0","0","0","0","0","0","0"); // 或者你想要的任何初始值
            }
        }
        if(coursemainService.initialization())
        {
            String []s=coursemainService.getCurrentTime().exec().split(",");
            if(s[0].length()>=8&&s[3].length()>=21)
            {
                String zc=s[0].substring(6,8);
                String week=s[3].substring(9,20);
                coursemainService.setCurtime(zc,week);
            }
            else
            {
                coursemainService.setCurtime("1","2024-2025-1");
                //              return Result.success("放假啦");
            }
            String jsonString=coursemainService.getTable().exec();
            List<Course> CourseList= JSONObject.parseArray(jsonString,Course.class);
            for(Course course:CourseList)
            {
                Resultcourse[(course.getKcsj().charAt(2)-'0')/2 ][(course.getKcsj().charAt(0)-'0')-1]=course;
            }
            System.out.println(Arrays.deepToString(Resultcourse));
            return Result.success(Resultcourse);
        }
        else
        {
            return Result.error("error");
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
