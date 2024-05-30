package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HygieneMapper {
    @Update("update coap.Hygiene set FirstRank=#{ Rank} where Dormitoryid=#{Dormitoryid}")
    void insertHygiene(Hygiene hygiene);
    @Select("select FirstRank from coap.Hygiene where Dormitoryid=#{Dormitoryid}")
    String selectHygieneByDormitoryid(String Dormitoryid);
}