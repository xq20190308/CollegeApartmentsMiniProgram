package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfo;
import com.william.collegeapartmentsbacke.service.StuClassInfoService;
import com.william.collegeapartmentsbacke.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/28 16:25
 * @Version: 1.0
 */

@RestController
@RequestMapping("/class")
@Slf4j
public class StuClassController {
    @Autowired
    private UserService userService;

    @Autowired
    private StuClassInfoService stuClassInfoService;

    @RequestMapping(value = "/get-classinfo",method = RequestMethod.GET)
    public Result getClassInfoByUserId(@RequestHeader("Authorization") String token) {
        String userId = userService.getUseridFromToken(token);
        StuClassInfo stuClassInfo = stuClassInfoService.getStuClassInfoByUserId(userId);
        log.info("stuClassInfo:{}", stuClassInfo);

        return Result.success(stuClassInfo);
    }
    @RequestMapping(value = "/get-all")
    public Result getAllClassInfo() {
        List<StuClassInfo> stuClassInfos = stuClassInfoService.getAllClassInfo();
        log.info("stuClassInfos:{}", stuClassInfos);
        return Result.success(stuClassInfos);
    }

}
