package com.william.collegeapartmentsbacke.service;

public interface PuInfoService {
    String Login(String username, String password) throws Exception;

    String getPuInfo(String username, String password) throws Exception;

    String getActivityInfo(String username, String password,Integer page) throws Exception;

    String joinActivity(String username, String password, long activityId) throws Exception;
}
