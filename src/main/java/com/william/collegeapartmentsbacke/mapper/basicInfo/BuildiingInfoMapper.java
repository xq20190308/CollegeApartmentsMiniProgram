package com.william.collegeapartmentsbacke.mapper.basicInfo;

import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.BuildingInfo;
import com.william.collegeapartmentsbacke.pojo.entity.basicInfo.CampusInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BuildiingInfoMapper {
    @Select("select * from coap.building_info order by building_name")
    List<BuildingInfo> selectAll();
}
