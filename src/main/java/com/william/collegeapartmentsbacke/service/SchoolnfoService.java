package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.schoolInfo.SchoolInfo;
import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.CampusInfoVO;
import com.william.collegeapartmentsbacke.pojo.vo.basicInfo.TotalSchoolInfoVO;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 20:08
 * @Version: 1.0
 */
public interface SchoolnfoService {
    TotalSchoolInfoVO getAllSchoolInfo();

}
