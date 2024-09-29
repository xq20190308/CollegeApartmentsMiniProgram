package com.william.collegeapartmentsbacke.service.impl;


import com.william.collegeapartmentsbacke.common.utils.HttpClientUtil;
import com.william.collegeapartmentsbacke.service.ElectricBillService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ElectricBillServicelmpl implements ElectricBillService {

    private static final String LoginURL= "https://my.sdust.edu.cn/cas/login";
    private static final String LoginType= "text";
    private Map<String, Object> param=new LinkedHashMap<>();
    @Override
    public String Login(String username, String password) throws Exception{
        param.put("username",username);
        param.put("type", LoginType);
        param.put("password", password);
        return HttpClientUtil.doPost4Json(LoginURL,param);
    }
}
