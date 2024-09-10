package com.william.collegeapartmentsbacke.service.impl;

import cn.hutool.json.JSONObject;
import com.william.collegeapartmentsbacke.common.utils.HttpClientUtil;
import com.william.collegeapartmentsbacke.service.PuInfoService;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PuInfoServiceImpl implements PuInfoService {
    private static final String LoginURL= "https://apis.pocketuni.net/uc/user/login";
    private static final String InfoURL="https://apis.pocketuni.net/apis/user/pc-info";
    private static final String ActivityURL="https://apis.pocketuni.net/apis/activity/list";
    private static final String JoinURL="https://apis.pocketuni.net/apis/activity/join";
    private static final int TIMEOUT = 3000;
    private static final long  SCHOOL_ID=208754666766336L;
    private Map<String, Object> param=new LinkedHashMap<>();

    @Override
    public String Login(String username, String password) throws Exception{
        param.put("userName",username);
        param.put("password",password);
        param.put("sid",SCHOOL_ID);
        param.put("device","pc");
        return HttpClientUtil.doPost4Json(LoginURL,param);
    }

    @Override
    public String getPuInfo(String username, String password) throws Exception {
        String result=this.Login(username,password);
        JSONObject jsonObject=new JSONObject(result);
        JSONObject data=jsonObject.getJSONObject("data");
        String AuthHeader= "Bear %s:%s".formatted(data.getStr("token"), SCHOOL_ID);
        return HttpClientUtil.doPost(InfoURL,null,AuthHeader);
    }

    @Override
    public String getActivityInfo(String username, String password,Integer page) throws Exception {
        String result=this.Login(username,password);
        JSONObject jsonObject=new JSONObject(result);
        JSONObject data=jsonObject.getJSONObject("data");
        String AuthHeader= "Bear %s:%s".formatted(data.getStr("token"), SCHOOL_ID);
        param.put("sort",0);
        param.put("page",page);
        param.put("limit",10);
        param.put("puType",0);
        JSONObject response=new JSONObject(HttpClientUtil.doPost4Json(ActivityURL,param,AuthHeader));
        JSONObject Data=response.getJSONObject("data");
        JSONObject pageInfo=Data.getJSONObject("pageInfo");
        if(response.getStr("code").equals("0"))
        {
            System.out.println(Data.getStr("list"));
            return Data.getStr("list");
        }
        else
            return "error";
    }

    @Override
    public String joinActivity(String username, String password,long activityId) throws Exception {
        String result=this.Login(username,password);
        JSONObject jsonObject=new JSONObject(result);
        JSONObject data=jsonObject.getJSONObject("data");
        String AuthHeader= "Bear %s:%s".formatted(data.getStr("token"), SCHOOL_ID);
        param.put("activityId",activityId);
        return HttpClientUtil.doPost4Json(JoinURL,param,AuthHeader);
    }
}
