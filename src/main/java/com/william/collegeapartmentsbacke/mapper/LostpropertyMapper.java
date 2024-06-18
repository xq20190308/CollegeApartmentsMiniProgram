package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LostpropertyMapper {
    @Update("insert into coap.Lostproperty (stuid,category,name,pickLocation,pickTime,describes,contactobject,filepath,status) values (#{stuid},#{category},#{name},#{pickLocation},#{pickTime},#{describes},#{contactobject},#{filepath},#{status})")
    void saveSubmit(Itemdata itemdata);

    @Select("select * from coap.Lostproperty where category=#{category} and status='已通过'")
    List<Itemdata> selectAll(String category);

    @Update("update  coap.Lostproperty set status=#{status} where id=#{id}")
    void updateAll(Itemdata itemdata);
}
