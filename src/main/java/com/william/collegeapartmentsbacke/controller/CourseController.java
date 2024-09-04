package com.william.collegeapartmentsbacke.controller;

import com.alibaba.fastjson.JSONObject;
import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.Course;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.User;
import com.william.collegeapartmentsbacke.service.CoursemainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@Slf4j
@RestController
@RequestMapping("/api")

public class CourseController {
    @Autowired
    private CoursemainService coursemainService;
    Course [][]Resultcourse=new Course[5][7];
    //获取课程表信息
    @NoNeedLogin
    @RequestMapping ("/obtainCourse")
    public Result test(@RequestBody User user) {
        // 设置用户账号和密码
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        // 初始化结果课程表
        for (int i = 0; i < Resultcourse.length; i++) {
            for (int j = 0; j < Resultcourse[i].length; j++) {
                Resultcourse[i][j] = new Course("0","0","0","0","0","0","0","0"); // 初始化课程对象
            }
        }
        // 判断是否成功登录
        if(coursemainService.initialization())
        {
            // 获取当前学期和周次信息
            String []s=coursemainService.getCurrentTime().exec().split(",");
            if(s[0].length()>=7&&s[3].length()>=21)
            {
                // 解析学年学期和周次
                String zc=s[0].substring(6);
                String week=s[3].substring(9,20);
                coursemainService.setCurtime(zc,week);
            }
            else
            {
                // 默认设置为下学期第一周
                coursemainService.setCurtime("1","2024-2025-1");
                //              return Result.success("放假啦");
            }
            // 获取课程表数据
            String jsonString=coursemainService.getTable().exec();
            // 解析JSON数据为课程对象列表
            List<Course> CourseList= JSONObject.parseArray(jsonString,Course.class);
            // 将课程对象填充到结果课程表中
            for(Course course:CourseList)
            {
                Resultcourse[(course.getKcsj().charAt(2)-'0')/2 ][(course.getKcsj().charAt(0)-'0')-1]=course;
            }
            // 打印课程表
            System.out.println(Arrays.deepToString(Resultcourse));
            // 返回成功结果，包含课程表
            return Result.success(Resultcourse);
        }
        else
        {
            // 返回错误结果
            return Result.error("error");
        }
    }

    //进行强智系统的登录初始化
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
    @NoNeedLogin
    @RequestMapping("/SelectCourse/{id}")
    public Result SelectCourse(@PathVariable("id")String id,@RequestBody User user) {
        // 设置用户账号和密码
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        // 初始化结果课程表
        for (int i = 0; i < Resultcourse.length; i++) {
            for (int j = 0; j < Resultcourse[i].length; j++) {
                Resultcourse[i][j] = new Course("0","0","0","0","0","0","0","0"); // 初始化课程对象
            }
        }
        // 判断是否成功登录
        if(coursemainService.initialization())
        {
            // 获取当前学期和周次信息
            String []s=coursemainService.getCurrentTime().exec().split(",");
            if(s[0].length()>=7&&s[3].length()>=21)
            {
                // 解析学年学期和周次
                coursemainService.setCurtime(id,s[3].substring(9,20));
            }
            else
            {
                // 默认设置为下学期第一周
                coursemainService.setCurtime("1","2024-2025-1");
                //              return Result.success("放假啦");
            }
            // 获取课程表数据
            String jsonString=coursemainService.getTable().exec();
            // 解析JSON数据为课程对象列表
            List<Course> CourseList= JSONObject.parseArray(jsonString,Course.class);
            // 将课程对象填充到结果课程表中
            for(Course course:CourseList)
            {
                Resultcourse[(course.getKcsj().charAt(2)-'0')/2 ][(course.getKcsj().charAt(0)-'0')-1]=course;
            }
            // 打印课程表
            System.out.println(Arrays.deepToString(Resultcourse));
            // 返回成功结果，包含课程表
            return Result.success(Resultcourse);
        }
        else
        {
            // 返回错误结果
            return Result.error("error");
        }
    }
}
