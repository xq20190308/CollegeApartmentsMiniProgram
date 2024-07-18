package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.SchoolInfoMapper;
import com.william.collegeapartmentsbacke.mapper.basicInfo.*;
import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfoDTO;
import com.william.collegeapartmentsbacke.pojo.entity.UserSchoolInfo;
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
    @Autowired
    private SchoolInfoMapper schoolInfoMapper;




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









    @Override
    public TotalSchoolInfoVO getAllSchoolInfo() {
        TotalSchoolInfoVO totalSchoolInfoVO = new TotalSchoolInfoVO();
        totalSchoolInfoVO.setCampusInfoVOList(new ArrayList<>());
        //从数据库取数据
        List<CampusInfo> campusInfos = campusInfoMapper.selectAll();
        List<GradeInfo> gradeInfos = gradeInfoMapper.selecAll();
        List<CollegeInfo> collegeInfos = collegeInfoMapper.selectAll();
        List<MajorInfo> majorInfos = majorInfoMapper.selectAll();
        List<ClassInfo> classInfos = classInfoMapper.selectAll();


        for (CampusInfo campusInfo : campusInfos) {
//            log.info("campusInfo: {}", campusInfo);
            Integer campusId = campusInfo.getCampusId();

            CampusInfoVO campusInfoVO = new CampusInfoVO();
            campusInfoVO.setCampusId(campusInfo.getCampusId());
            campusInfoVO.setCampusName(campusInfo.getCampusName());
            campusInfoVO.setGrades(new ArrayList<>());
            for (GradeInfo gradeInfo : gradeInfos) {
//                log.info("gradeInfo: {}", gradeInfo);
                Integer gradeId = gradeInfo.getGradeId();

                if(gradeInfo.getCampusId() != campusId){
                    continue;
                }

                GradeInfoVO gradeInfoVO = new GradeInfoVO();
                gradeInfoVO.setGradeId(gradeInfo.getGradeId());
                gradeInfoVO.setGradeName(gradeInfo.getGradeName());
                gradeInfoVO.setColleges(new ArrayList<>());
                for (CollegeInfo collegeInfo : collegeInfos) {
//                    log.info("collegeInfo: {}", collegeInfo);
                    Integer collegeId = collegeInfo.getCollegeId();

                    if(collegeInfo.getGradeId() != gradeId){
                        continue;
                    }

                    CollegeInfoVO collegeInfoVO = new CollegeInfoVO();
                    collegeInfoVO.setCollegeId(collegeInfo.getCollegeId());
                    collegeInfoVO.setCollegeName(collegeInfo.getCollegeName());
                    collegeInfoVO.setMajors(new ArrayList<>());
                    for (MajorInfo majorInfo : majorInfos) {
//                        log.info("majorInfo: {}", majorInfo);
                        Integer majorId = majorInfo.getMajorId();

                        MajorInfoVO majorInfoVO = new MajorInfoVO();
                        majorInfoVO.setMajorName(majorInfo.getMajorName());
                        majorInfoVO.setMajorId(majorId);
                        majorInfoVO.setClasses(new ArrayList<>());

                        if (majorInfo.getCollegeId() != collegeId) {
                            continue;
                        }

                        for (ClassInfo classInfo : classInfos) {
                            ClassInfoVO classInfoVO = new ClassInfoVO(classInfo.getClassId(), classInfo.getClassName());
                            if(classInfo.getMajorId() != majorInfo.getMajorId()){
                                continue;
                            }
                            log.info("classInfo:" + classInfo.getClassId() + ",classInfo:" + classInfo.getClassName());
                            majorInfoVO.getClasses().add(classInfoVO);
                        }
//                        classInfos.removeIf(classInfo -> classInfo.getMajorId() == majorId);
                        collegeInfoVO.getMajors().add(majorInfoVO);
                    }
//                    majorInfos.removeIf(majorInfo -> majorInfo.getMajorId() == collegeId);
                    gradeInfoVO.getColleges().add(collegeInfoVO);
                }
//                collegeInfos.removeIf(collegeInfo -> collegeInfo.getCollegeId() == gradeId);
                campusInfoVO.getGrades().add(gradeInfoVO);
            }
            totalSchoolInfoVO.getCampusInfoVOList().add(campusInfoVO);
        }

        return totalSchoolInfoVO;
    }


//    @Override
//    public TotalSchoolInfoVO getAllSchoolInfoBetter() {
//        TotalSchoolInfoVO totalSchoolInfoVO = new TotalSchoolInfoVO();
//        totalSchoolInfoVO.setCampusInfoVOList(new ArrayList<>());
//        //从数据库取数据
//        List<CampusInfo> campusInfos = campusInfoMapper.selectAll();
//        List<GradeInfo> gradeInfos = gradeInfoMapper.selecAll();
//        List<CollegeInfo> collegeInfos = collegeInfoMapper.selectAll();
//        List<MajorInfo> majorInfos = majorInfoMapper.selectAll();
//        List<ClassInfo> classInfos = classInfoMapper.selectAll();
//
//        int campusi = 0;
//        int gradei = 0;
//        int collegei = 0;
//        int majori = 0;
//        int classi = 0;
//
//
//        //初始化返回给前端的对象
//
//        for ( ;campusi < campusInfos.size(); campusi++) {
//            CampusInfo curCampusInfo= campusInfos.get(campusi);
//
//            CampusInfoVO campusInfoVO = new CampusInfoVO();
//            campusInfoVO.setCampusId(curCampusInfo.getCampusId());
//            campusInfoVO.setCampusName(curCampusInfo.getCampusName());
//            campusInfoVO.setGrades(new ArrayList<>());
//            for (; gradei < gradeInfos.size(); gradei++) {
//
//                GradeInfo curGradeInfo = gradeInfos.get(gradei);
//
//                if(curCampusInfo.getCampusId() != curGradeInfo.getCampusId()){
//                    break;
//                }
//
//                GradeInfoVO gradeInfoVO = new GradeInfoVO();
//                gradeInfoVO.setGradeId(curGradeInfo.getGradeId());
//                gradeInfoVO.setGradeName(curGradeInfo.getGradeName());
//                gradeInfoVO.setColleges(new ArrayList<>());
//                for (;collegei < collegeInfos.size(); collegei++) {
//
//                    CollegeInfo curCollegeInfo = collegeInfos.get(collegei);
//
//                    if(curGradeInfo.getGradeId() != curCollegeInfo.getGradeId()){
//                        break;
//                    }
//
//                    CollegeInfoVO collegeInfoVO = new CollegeInfoVO();
//                    collegeInfoVO.setCollegeId(curCollegeInfo.getCollegeId());
//                    collegeInfoVO.setCollegeName(curCollegeInfo.getCollegeName());
//                    collegeInfoVO.setMajors(new ArrayList<>());
//                    for (; majori < majorInfos.size(); majori++) {
//
//                        MajorInfo curMajorInfo = majorInfos.get(majori);
//
//                        if(curCollegeInfo.getCollegeId() != curMajorInfo.getCollegeId()){
//                            break;
//                        }
//
//                        MajorInfoVO majorInfoVO = new MajorInfoVO();
//                        majorInfoVO.setMajorName(curMajorInfo.getMajorName());
//                        majorInfoVO.setMajorId(curMajorInfo.getMajorId());
//                        majorInfoVO.setClasses(new ArrayList<>());
//
//                        for(;classi < classInfos.size(); classi++){
//                            ClassInfo curClassInfo = classInfos.get(classi);
//
//                            if(curMajorInfo.getMajorId() != curClassInfo.getMajorId())
//                            {
//                                break;
//                            }
//                            ClassInfoVO classInfoVO = new ClassInfoVO(curClassInfo.getClassId(), curClassInfo.getClassName());
//                            majorInfoVO.getClasses().add(classInfoVO);
//                        }
//                        collegeInfoVO.getMajors().add(majorInfoVO);
//                    }
//                    gradeInfoVO.getColleges().add(collegeInfoVO);
//                }
//                campusInfoVO.getGrades().add(gradeInfoVO);
//            }
//            totalSchoolInfoVO.getCampusInfoVOList().add(campusInfoVO);
//        }
//        return totalSchoolInfoVO;
//    }
}
