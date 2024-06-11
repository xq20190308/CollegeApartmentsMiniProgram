package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.dto.HygieneDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import com.william.collegeapartmentsbacke.service.HygieneService;
import com.william.collegeapartmentsbacke.service.impl.HygieneServicelmpl;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HygieneMapper {
    @UpdateProvider(type = HygieneServicelmpl.class, method = "getDynamicUpdateSql")
    void insertHygiene(Hygiene hygiene,String dromaticSql);
    @SelectProvider(type=HygieneServicelmpl.class,method="selectHygieneByDormitoryid")
    HygieneDTO selectHygieneByDormitoryid(String id);

    String updateHygiene(Hygiene hygiene);

}