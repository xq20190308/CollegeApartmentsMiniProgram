package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.PuUser;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.service.PuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PuInfoController {
    @Autowired
    PuInfoService puInfoService;
    @NoNeedLogin
    @RequestMapping("login")
    public Result login(@RequestBody PuUser puUser)
    {
        try {
            if(puInfoService.Login(puUser.getUsername(),puUser.getPassword())==null)
                return Result.error("用户名或密码错误");
            else
                return Result.success();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @NoNeedLogin
    @RequestMapping("getPuInfo")
    public String getPuInfo(@RequestBody PuUser puUser)
    {
        try {
            return puInfoService.getPuInfo(puUser.getUsername(),puUser.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
