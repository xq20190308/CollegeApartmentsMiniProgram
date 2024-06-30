package com.william.collegeapartmentsbacke.mapper.basicInfo;

import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.CollegeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 20:04
 * @Version: 1.0
 */
@Mapper
public interface CollegeInfoMapper {

    @Select("select * from coap.college_info where grade_id = #{gradeId}")
    List<CollegeInfo> selectCollegeInfosByGradeId(Integer gradeId);

    @Select("select * from coap.college_info order by grade_id")
    List<CollegeInfo> selectAll();
}
