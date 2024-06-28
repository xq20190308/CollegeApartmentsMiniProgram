package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfo;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/28 16:50
 * @Version: 1.0
 */
public interface StuClassInfoService {
    public StuClassInfo getStuClassInfoByUserId(String userId);

    List<StuClassInfo> getAllClassInfo();
}
