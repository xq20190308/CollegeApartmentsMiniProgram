package com.william.collegeapartmentsbacke.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.william.collegeapartmentsbacke.pojo.dto.PageDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LostpropertyMapper {
    @Update("insert into coap.lostproperty (stuid,category,name,pick_location,pick_time,describes,contact_object,file_path,status,solve) values (#{stuid},#{category},#{name},#{pick_location},#{pick_time},#{describes},#{contact_object},#{file_path},#{status},#{solve})")
    void saveSubmit(Itemdata itemdata);

    @Select("select * from coap.lostproperty where category=#{category} and status=1")
    IPage<Itemdata> selectAll(String category, Page<PageDTO> page, PageDTO pagePara);

    @Select("select * from coap.lostproperty where stuid=#{stuid}")
    List<Itemdata> selectUserAll(String stuid);

    @Update("update  coap.lostproperty set status=#{status} where id=#{id}")
    void updateAll(Itemdata itemdata);

    @Update("update coap.lostproperty set solve=#{solve} where id=#{id}")
    void updateSolve(Integer id,Integer solve);

    @Delete("delete from coap.lostproperty where id=#{id}")
    void deleteData(Integer id);
}
