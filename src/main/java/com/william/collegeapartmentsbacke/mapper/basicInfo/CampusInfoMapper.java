package com.william.collegeapartmentsbacke.mapper.basicInfo;

import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.CampusInfo;
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
public interface CampusInfoMapper {

    @Select("select * from coap.campus_info order by campus_id")
    List<CampusInfo> selectAll();

}
