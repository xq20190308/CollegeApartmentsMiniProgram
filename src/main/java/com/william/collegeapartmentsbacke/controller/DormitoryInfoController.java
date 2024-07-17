package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.TotalDormitoryInfoVO;
import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.TotalSchoolInfoVO;
import com.william.collegeapartmentsbacke.service.DormitoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/dormitory-info")
public class DormitoryInfoController {
    @Autowired
    private DormitoryService dormitoryService;
    @RequestMapping(value = "/infos")
    public Result getDormitoryInfo(){
        TotalDormitoryInfoVO totalDormitoryInfoVO =  dormitoryService.getAllDormitoryInfo();
        log.info("totalDormitoryInfoVO:{}", totalDormitoryInfoVO);
        return Result.success(totalDormitoryInfoVO);
    }
}
