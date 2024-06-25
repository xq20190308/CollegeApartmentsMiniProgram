package com.william.collegeapartmentsbacke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.william.collegeapartmentsbacke.pojo.dto.HygieneDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import com.william.collegeapartmentsbacke.service.impl.HygieneServicelmpl;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HygieneMapper extends BaseMapper<Hygiene> {
    @UpdateProvider(type = HygieneServicelmpl.class, method = "getDynamicUpdateSql")
    void insertHygiene(Hygiene hygiene,String dromaticSql);
    @SelectProvider(type=HygieneServicelmpl.class,method="selectHygieneByDormitoryid")
    HygieneDTO selectHygieneByDormitoryid(String id);
//    boolean saveBatch(List<Hygiene> hygienes);
//    String updateHygiene(Hygiene hygiene);
}