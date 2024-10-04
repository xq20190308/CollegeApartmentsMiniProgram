package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.userInfo.StuClassInfoDTO;
import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.TotalSchoolInfoVO;
import com.william.collegeapartmentsbacke.service.SchoolnfoService;
import com.william.collegeapartmentsbacke.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 20:10
 * @Version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("/school-info")
public class SchoolInfoController {
    @Autowired
    private SchoolnfoService schoolnfoService;


    @Autowired
    private UserService userService;


    @GetMapping("/getClassinfo")
    @NoNeedLogin
    public AjaxResult getClassInfoByUserId(@RequestParam("userid") String userid) {
        StuClassInfoDTO stuClassInfo = schoolnfoService.getStuClassInfoByUserIdBetter(userid);
        log.info("stuClassInfo:{}", stuClassInfo);
        log.info("查询某人班级信息：stuClassInfo:{}", stuClassInfo);
        return AjaxResult.success(stuClassInfo);
    }

    @RequestMapping("/get-classinfo")
    public AjaxResult getClassInfoByUserIdBetter(@RequestHeader("Authorization") String token) {
        String userId = userService.getUseridFromToken(token);
        StuClassInfoDTO stuClassInfo = schoolnfoService.getStuClassInfoByUserIdBetter(userId);
        log.info("stuClassInfo:{}", stuClassInfo);
        log.info("查询某人班级信息：stuClassInfo:{}", stuClassInfo);
        return AjaxResult.success(stuClassInfo);
    }

    @RequestMapping(value = "/infos")
    public AjaxResult getSchoolInfoBetter(){
        TotalSchoolInfoVO totalSchoolInfoVO =  schoolnfoService.getAllSchoolInfo();
        log.info("查询所有校区-年级-学院-专业-班级等：totalSchoolInfoVO:{}", totalSchoolInfoVO);
        return AjaxResult.success(totalSchoolInfoVO);
    }

}
