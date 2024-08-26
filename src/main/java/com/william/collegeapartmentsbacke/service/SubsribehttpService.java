package com.william.collegeapartmentsbacke.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface SubsribehttpService {
    public String sendSubscribeMessage(String accessToken, String openid, String templateId, String page, String data);
    public String refreshAccessToken(String appid, String secret);
    public Object getPubTemplates(String accessToken);

    Object getCategory(String accessToken);

    Object getTemplates(String accessToken);

    public Object getTemplateKeys(String accessToken, String tid);
}