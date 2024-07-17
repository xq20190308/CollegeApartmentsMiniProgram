package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfoDTO;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/28 16:50
 * @Version: 1.0
 */
public interface StuClassInfoService {
    StuClassInfoDTO getStuClassInfoByUserId(String userId);

    List<StuClassInfoDTO> getAllClassInfo();

    StuClassInfoDTO getStuClassInfoByUserIdBetter(String userId);
}
