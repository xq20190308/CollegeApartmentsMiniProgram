package com.william.collegeapartmentsbacke.controller;

import cn.hutool.json.JSONObject;
import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.ElectricBillUser;
import com.william.collegeapartmentsbacke.service.ElectricBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elecbill")
public class ElectricBillController {
    @Autowired
    ElectricBillService electricBillService;
    @NoNeedLogin
    @RequestMapping("login")
    public AjaxResult login(@RequestBody ElectricBillUser electricBillUser)
    {
        try {
            String response = electricBillService.Login(electricBillUser.getUsername(), electricBillUser.getPassword());
            JSONObject jsonObject=new JSONObject(response);
            JSONObject data=jsonObject.getJSONObject("data");
            if(jsonObject.getStr("code").equals("0"))
                return AjaxResult.success(data.getStr("token"));
            else
                return AjaxResult.error("用户名或密码错误");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
