package com.william.collegeapartmentsbacke.service;

import com.alibaba.fastjson.JSONPObject;
import com.william.collegeapartmentsbacke.pojo.entity.Result;

import java.util.List;

public interface PuInfoService {
    String Login(String username, String password) throws Exception;

    String getPuInfo(String username, String password) throws Exception;
}
