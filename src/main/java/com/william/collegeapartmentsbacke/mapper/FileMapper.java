package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;


@Mapper
public interface FileMapper {

    @Update("insert into  coap.Filedata (id,user_id,name,type,path,data) values(#{id},#{userid},#{name},#{type},#{path},#{data})")
    void savefile(Uploadfile file);

    @Select("select path from coap.Filedata where id=#{id}")
    String selectfile(String id);

    @Select("select LAST_INSERT_ID()")
    String getLatestId();

    @Delete("Delete *from coap.Filedata where path=#{Url}")
    String deletefile(String Url);
}
