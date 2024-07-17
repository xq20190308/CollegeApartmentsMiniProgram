package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfoDTO;
import com.william.collegeapartmentsbacke.pojo.entity.StuClassInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/28 16:38
 * @Version: 1.0
 */
@Mapper
public interface StuClassInfoMapper {
    @Select("select c.* from coap.class_info c join coap.user u on c.class_id = u.class_id where u.userid = #{userId}")
    StuClassInfoDTO getClassInfoByUserId(String userId);

    @Select("select * from coap.class_info")
    List<StuClassInfoDTO> getAllClassInfo();
}
