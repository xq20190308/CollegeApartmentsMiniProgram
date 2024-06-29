package com.william.collegeapartmentsbacke.mapper.basicInfo;

import com.william.collegeapartmentsbacke.pojo.dto.schoolInfo.SchoolInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 22:17
 * @Version: 1.0
 */
@Mapper
public interface SchoolInfoMapper {


    List<String> selectUserIdBySchoolInfo(SchoolInfo schoolInfo);
}
