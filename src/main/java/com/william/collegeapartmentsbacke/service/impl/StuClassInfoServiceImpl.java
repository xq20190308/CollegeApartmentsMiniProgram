package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.StuClassInfoMapper;
import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfo;
import com.william.collegeapartmentsbacke.service.StuClassInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/28 16:54
 * @Version: 1.0
 */
@Service
@Slf4j
public class StuClassInfoServiceImpl implements StuClassInfoService {

    @Autowired
    private StuClassInfoMapper stuClassInfoMapper;

    /**
     * @param userId
     * @return
     */
    @Override
    public StuClassInfo getStuClassInfoByUserId(String userId) {
        StuClassInfo stuClassInfo = stuClassInfoMapper.getClassInfoByUserId(userId);
//        log.info("stuClassInfo:{}", stuClassInfo);


        return stuClassInfo;
    }

    /**
     * @param
     * @return
     */
    @Override
    public List<StuClassInfo> getAllClassInfo() {
        List<StuClassInfo> stuClassInfos = stuClassInfoMapper.getAllClassInfo();
        return stuClassInfos;
    }
}
