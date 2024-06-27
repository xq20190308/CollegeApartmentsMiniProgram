package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LostpropertyMapper {
    @Update("insert into coap.lostproperty (stuid,category,name,pickLocation,pickTime,describes,contactobject,filepath,status,solve) values (#{stuid},#{category},#{name},#{pickLocation},#{pickTime},#{describes},#{contactobject},#{filepath},#{status},#{solve})")
    void saveSubmit(Itemdata itemdata);

    @Select("select * from coap.lostproperty where category=#{category} and status=1")
    List<Itemdata> selectAll(String category);

    @Update("update  coap.lostproperty set status=#{status} where id=#{id}")
    void updateAll(Itemdata itemdata);

    @Delete("delete from coap.lostproperty where id=#{id}")
    void deleteData(Integer id);
}
