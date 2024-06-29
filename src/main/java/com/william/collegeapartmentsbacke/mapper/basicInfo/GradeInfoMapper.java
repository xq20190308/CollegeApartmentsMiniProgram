package com.william.collegeapartmentsbacke.mapper.basicInfo;

import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.GradeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 20:06
 * @Version: 1.0
 */
@Mapper
public interface GradeInfoMapper {

    @Select("select * from coap.grade_info where campus_id = #{campusId}")
    List<GradeInfo> selectAllGradeInfoByCampusId(Integer campusId);
}
