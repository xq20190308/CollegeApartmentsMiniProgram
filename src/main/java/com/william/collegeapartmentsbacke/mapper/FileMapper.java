package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;


@Mapper
public interface FileMapper {

    @Update("insert into  coap.Filedata (id,user_id,name,Type,Path,data,used_type) values(#{id},#{userid},#{name},#{Type},#{Path},#{data},#{usedType})")
    @Transactional
    void savefile(Uploadfile file);

    @Select("select path from coap.Filedata where id=#{id} and used_type = #{usedType}")
    String selectfile(String id,String usedType);
}
