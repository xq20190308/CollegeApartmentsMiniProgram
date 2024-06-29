package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.basicInfo.*;
import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.*;
import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.*;
import com.william.collegeapartmentsbacke.service.SchoolnfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 20:08
 * @Version: 1.0
 */
@Slf4j
@Service
public class SchoolInfoServiceImpl implements SchoolnfoService {
    @Autowired
    private CampusInfoMapper campusInfoMapper;
    @Autowired
    private GradeInfoMapper gradeInfoMapper;
    @Autowired
    private CollegeInfoMapper collegeInfoMapper;
    @Autowired
    private MajorInfoMapper majorInfoMapper;
    @Autowired
    private ClassInfoMapper classInfoMapper;




    @Override
    public TotalSchoolInfoVO getAllSchoolInfo() {
        //返回给Controller的数组
        TotalSchoolInfoVO totalSchoolInfoVO = new TotalSchoolInfoVO();
        totalSchoolInfoVO.setCampusInfoVOList(new ArrayList<>());

        List<CampusInfo> campusInfos = campusInfoMapper.selectAll();

        for (CampusInfo campusInfo : campusInfos) {
            log.info("campusInfo: {}", campusInfo);
            Integer campusId = campusInfo.getCampusId();
            List<GradeInfo> gradeInfos = gradeInfoMapper.selectAllGradeInfoByCampusId(campusId);

            CampusInfoVO campusInfoVO = new CampusInfoVO();
            campusInfoVO.setCampusId(campusInfo.getCampusId());
            campusInfoVO.setCampusName(campusInfo.getCampusName());
            campusInfoVO.setGrades(new ArrayList<>());
            for (GradeInfo gradeInfo : gradeInfos) {
                log.info("gradeInfo: {}", gradeInfo);
                Integer gradeId = gradeInfo.getGradeId();
                List<CollegeInfo> collegeInfos = collegeInfoMapper.selectCollegeInfosByGradeId(gradeId);

                GradeInfoVO gradeInfoVO = new GradeInfoVO();
                gradeInfoVO.setGradeId(gradeInfo.getGradeId());
                gradeInfoVO.setGradeName(gradeInfo.getGradeName());
                gradeInfoVO.setColleges(new ArrayList<>());
                for (CollegeInfo collegeInfo : collegeInfos) {
                    log.info("collegeInfo: {}", collegeInfo);
                    Integer collegeId = collegeInfo.getCollegeId();
                    List<MajorInfo> majorInfos = majorInfoMapper.getMajorInfosByCollegeId(collegeId);

                    CollegeInfoVO collegeInfoVO = new CollegeInfoVO();
                    collegeInfoVO.setCollegeId(collegeInfo.getCollegeId());
                    collegeInfoVO.setCollegeName(collegeInfo.getCollegeName());
                    collegeInfoVO.setMajors(new ArrayList<>());
                    for (MajorInfo majorInfo : majorInfos) {
                        log.info("majorInfo: {}", majorInfo);
                        Integer majorId = majorInfo.getMajorId();
                        List<ClassInfo> classInfos = classInfoMapper.getClassInfosByMajorId(majorId);

                        MajorInfoVO majorInfoVO = new MajorInfoVO();
                        majorInfoVO.setMajorName(majorInfo.getMajorName());
                        majorInfoVO.setMajorId(majorId);
                        majorInfoVO.setClasses(new ArrayList<>());
                        for (ClassInfo classInfo : classInfos) {
                            log.info("classInfo: {}", classInfo);
                            ClassInfoVO classInfoVO = new ClassInfoVO(classInfo.getClassId(), classInfo.getClassName());
                            majorInfoVO.getClasses().add(classInfoVO);
                        }
                        collegeInfoVO.getMajors().add(majorInfoVO);
                    }
                    gradeInfoVO.getColleges().add(collegeInfoVO);
                }
                campusInfoVO.getGrades().add(gradeInfoVO);
            }
            totalSchoolInfoVO.getCampusInfoVOList().add(campusInfoVO);
        }

        return totalSchoolInfoVO;
    }
}
