package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LostpropertyMapper {
    @Update("insert into coap.Lostproperty (category,pickLocation,pickTime,desicribles,contactobject,filepath) values (#{category},#{pickLocation},#{pickTime},#{desicribles},#{contactobject},#{filepath})")
    void saveSubmit(Itemdata itemdata);

    @Select("select * from coap.Lostproperty where category=#{category}")
    List<Itemdata> selectAll(String category);
}
