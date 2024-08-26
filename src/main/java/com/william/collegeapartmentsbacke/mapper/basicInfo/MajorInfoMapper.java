package com.william.collegeapartmentsbacke.mapper.basicInfo;

import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.MajorInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/29 20:07
 * @Version: 1.0
 */
@Mapper
public interface MajorInfoMapper {

    @Select("select * from coap.major_info where college_id = #{collegeId}")
    List<MajorInfo> getMajorInfosByCollegeId(Integer collegeId);

    @Select("select  * from coap.major_info order by college_id")
    List<MajorInfo> selectAll();

    @Select("select major_name from coap.major_info where major_id = #{majorId}")
    String getMajorNameById(Integer majorId);
}
