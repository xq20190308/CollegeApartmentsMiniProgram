package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import com.william.collegeapartmentsbacke.service.HygieneService;
import com.william.collegeapartmentsbacke.service.impl.HygieneServicelmpl;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HygieneMapper {
    @UpdateProvider(type = HygieneServicelmpl.class, method = "getDynamicUpdateSql")
    void insertHygiene(Hygiene hygiene,String dromaticSql);
    @SelectProvider(type=HygieneServicelmpl.class,method="selectHygieneByDormitoryid")
    String selectHygieneByDormitoryid(String week,String Dormitoryid);

    String updateHygiene(Hygiene hygiene);

}