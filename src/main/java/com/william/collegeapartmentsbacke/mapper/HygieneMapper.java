package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HygieneMapper {
    @Update("update coap.Hygiene set Dormitoryid=#{Dormitoryid},Rank=#{Rank} WHERE Dormitoryid=#{Dormitoryid}")
    void insertHygiene(Hygiene hygiene);
}