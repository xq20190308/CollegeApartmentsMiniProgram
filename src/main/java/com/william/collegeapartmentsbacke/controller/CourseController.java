package com.william.collegeapartmentsbacke.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.Course;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.userInfo.User;
import com.william.collegeapartmentsbacke.service.CoursemainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * @author 王
 */
@Slf4j
@RestController
@RequestMapping("/api")

public class CourseController {
    @Autowired
    private CoursemainService coursemainService;
    Course [][]Resultcourse=new Course[7][5];
    //获取课程表信息
    @NoNeedLogin
    @RequestMapping ("/obtainCourse")
    public AjaxResult test(@RequestBody User user) {
        // 设置用户账号和密码
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        // 初始化结果课程表
        for (int i = 0; i < Resultcourse.length; i++) {
            for (int j = 0; j < Resultcourse[i].length; j++) {
                Resultcourse[i][j] = new Course("1","1","1","","","","",""); // 初始化课程对象
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
                Resultcourse[ (course.getKcsj().charAt(0)-'0')-1][(course.getKcsj().charAt(2)-'0')/2]=course;
            }
            // 打印课程表
            System.out.println(Arrays.deepToString(Resultcourse));
            // 返回成功结果，包含课程表
            return AjaxResult.success(Resultcourse);
        }
        else
        {
            // 返回错误结果
            return AjaxResult.error("error");
        }
    }

    //进行强智系统的登录初始化
    @RequestMapping("/Login")
    public AjaxResult loginInital(@RequestBody User user) {
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        if(coursemainService.initialization())
        {
            Map<String,String> account=new HashMap<>();
            account.put("password",user.getPassword());
            account.put("username",user.getUsername());
            return AjaxResult.success(account);
        }
        else
        {
            return AjaxResult.error("password invalid");
        }

    }
    @NoNeedLogin
    @RequestMapping("/SelectCourse/{id}")
    public AjaxResult SelectCourse(@PathVariable("id")String id,@RequestBody User user) {
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
                Resultcourse[ (course.getKcsj().charAt(0)-'0')-1][(course.getKcsj().charAt(2)-'0')/2]=course;
            }
            // 打印课程表
            for (int i = 0; i < Resultcourse.length; i++) {
                System.out.println(Arrays.deepToString(Resultcourse[i]));
            }

            // 返回成功结果，包含课程表
            return AjaxResult.success(Resultcourse);
        }
        else
        {
            // 返回错误结果
            return AjaxResult.error("error");
        }
    }

    @NoNeedLogin
    @RequestMapping("/getGrade/{term}")
    public String SelectCourseByWeek(@PathVariable("term")String term,@RequestBody User user) {
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        if(coursemainService.initialization())
        {
            return coursemainService.getGrade().setTerm(term).exec();
        }
        else {
            return"error";
        }
    }

    @NoNeedLogin
    @RequestMapping("/getExamInfo")
    public AjaxResult SelectExamInfo(@RequestBody User user) {
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        if(coursemainService.initialization())
        {
            return AjaxResult.success(coursemainService.getExamInfo().exec());
        }
        else {
            return AjaxResult.error("error");
        }
    }

    @NoNeedLogin
    @RequestMapping("/getClassroom/{idleTime}/{weekDay}/{buildingNum}")
    public  String selectClassroom(@PathVariable("idleTime")String idleTime,@PathVariable("weekDay") String weekDay,@PathVariable("buildingNum") String buildingNum, @RequestBody User user) {
        coursemainService.setAccount(user.getUsername(), user.getPassword());
        if(coursemainService.initialization())
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // 获取当前日期
            LocalDate currentDate = LocalDate.now();
            // 计算当前日期是周几
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            String dayOfWeekStr = dayOfWeek.name();
            LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
            LocalDate localDate = startOfWeek.plusDays(Integer.parseInt(weekDay)-1);
            String formattedDate = localDate.format(formatter);
            System.out.println("Date: " + formattedDate + ", Day of Week: " + localDate.getDayOfWeek().name());
            String jsonString = coursemainService.getClassroom(idleTime,formattedDate).exec();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 解析JSON字符串为JsonNode对象
                JsonNode rootNode = objectMapper.readTree(jsonString);

                // 创建一个列表来存储所有的jsmc值
                List<ObjectNode> jsmcList = new ArrayList<>();

                for (JsonNode node : rootNode) {
                    JsonNode jxl = node.get("jxl");
                    JsonNode jsList = node.get("jsList");

                    // 筛选出jxl中含有"青岛校区-"的节点
                    if (jxl != null && jxl.asText().contains("青岛校区-"+buildingNum) && jsList != null && jsList.isArray()) {
                        ArrayNode perJsmcArray = objectMapper.createArrayNode();
                        for (JsonNode jsNode : jsList) {
                            JsonNode jsmcNode = jsNode.get("jsmc");
                            if (jsmcNode != null) {
                                perJsmcArray.add(jsmcNode.asText());
                            }
                        }

                        ObjectNode perJsmcObject = objectMapper.createObjectNode();
                        perJsmcObject.put("jxl", jxl.asText());
                        perJsmcObject.set("jsmc", perJsmcArray);
                        jsmcList.add(perJsmcObject);
                    }
                }

                // 将jsmcList转换为JSON字符串
                ArrayNode resultArray = objectMapper.createArrayNode();
                for (ObjectNode jsmcObject : jsmcList) {
                    resultArray.add(jsmcObject);
                }

                return resultArray.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return "Error parsing JSON";
            }
        }
        else {
            return null;
        }
    }

}
