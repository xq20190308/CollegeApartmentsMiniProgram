package com.william.collegeapartmentsbacke.mapper.basicInfo;

import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.ClassInfo;
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
public interface ClassInfoMapper {

    @Select("select * from coap.class_info where major_id = #{majorId}")
    List<ClassInfo> getClassInfosByMajorId(Integer majorId);

    @Select("select * from coap.class_info order by major_id")
    List<ClassInfo> selectAll();
}
