package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.dto.schoolInfo.SchoolInfo;
import com.william.collegeapartmentsbacke.pojo.entity.userInfo.UserSchoolInfo;
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

    @Select("select * from coap.user_school_info where user_id = #{userId}")
    UserSchoolInfo selectUserSchoolInfoByUserId(String userId);


//
//    @Select("select")
//    List<UserSchoolInfo> selectAllDormitory();
}
