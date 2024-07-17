package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.StuClassInfoMapper;
import com.william.collegeapartmentsbacke.mapper.basicInfo.*;
import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfoDTO;
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

    @Autowired
    private SchoolInfoMapper schoolInfoMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private MajorInfoMapper majorInfoMapper;
    @Autowired
    private CollegeInfoMapper collegeInfoMapper;
    @Autowired
    private GradeInfoMapper gradeInfoMapper;
    @Autowired
    private CampusInfoMapper campusInfoMapper;


    /**
     * @param userId
     * @return
     */
    @Override
    public StuClassInfoDTO getStuClassInfoByUserId(String userId) {
        StuClassInfoDTO stuClassInfo = stuClassInfoMapper.getClassInfoByUserId(userId);
//        log.info("stuClassInfo:{}", stuClassInfo);


        return stuClassInfo;
    }

    /**
     * @param
     * @return
     */
    @Override
    public List<StuClassInfoDTO> getAllClassInfo() {
        List<StuClassInfoDTO> stuClassInfos = stuClassInfoMapper.getAllClassInfo();
        return stuClassInfos;
    }

    @Override
    public StuClassInfoDTO getStuClassInfoByUserIdBetter(String userId) {
        UserSchoolInfo userSchoolInfo = schoolInfoMapper.selectUserSchoolInfoByUserId(userId);
        String campusName = campusInfoMapper.getCampusNameById(userSchoolInfo.getCampusId());
        String gradeName = gradeInfoMapper.getGeadeNameById(userSchoolInfo.getGradeId());
        String collegeName = collegeInfoMapper.getCollegeNameById(userSchoolInfo.getCollegeId());
        String majorName = majorInfoMapper.getMajorNameById(userSchoolInfo.getMajorId());
        String className = classInfoMapper.getClassNameById(userSchoolInfo.getClassId());

        StuClassInfoDTO stuClassInfoDTO = StuClassInfoDTO.builder()
                .campusName(campusName)
                .gradeName(gradeName)
                .collegeName(collegeName)
                .majorName(majorName)
                .className(className)
                .build();


        return stuClassInfoDTO;
    }
}
