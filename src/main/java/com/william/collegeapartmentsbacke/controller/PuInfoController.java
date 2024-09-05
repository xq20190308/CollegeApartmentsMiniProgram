package com.william.collegeapartmentsbacke.controller;

import cn.hutool.json.JSONObject;
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
            String response=puInfoService.Login(puUser.getUsername(),puUser.getPassword());
            JSONObject jsonObject=new JSONObject(response);
            JSONObject data=jsonObject.getJSONObject("data");
            if(jsonObject.getStr("code").equals("0"))
                return Result.success(data.getStr("token"));
            else
                return Result.error("用户名或密码错误");
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

    @NoNeedLogin
    @RequestMapping("getActivityInfo")
    public String getActivityInfo(@RequestBody PuUser puUser)
    {
        try {
            return puInfoService.getActivityInfo(puUser.getUsername(),puUser.getPassword(),puUser.getRequestPage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @NoNeedLogin
    @RequestMapping("joinActivity")
    public String joinActivity(@RequestBody PuUser puUser)
    {
        try {
            return puInfoService.joinActivity(puUser.getUsername(),puUser.getPassword(),puUser.getActivityId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
