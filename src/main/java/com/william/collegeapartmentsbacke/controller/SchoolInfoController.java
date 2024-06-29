package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.TotalSchoolInfoVO;
import com.william.collegeapartmentsbacke.service.SchoolnfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @NoNeedLogin
    @RequestMapping(value = "/infos")
    public Result getSchoolInfo(){
        TotalSchoolInfoVO totalSchoolInfoVO =  schoolnfoService.getAllSchoolInfo();
        log.info("totalSchoolInfoVO:{}", totalSchoolInfoVO);
        return Result.success(totalSchoolInfoVO);
    }

}
