package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;


@Mapper
public interface FileMapper {

    @Insert("INSERT INTO coap.filedata (id, user_id, name, type, path, data) VALUES (#{id}, #{userId}, #{name}, #{type}, #{path}, #{data})")
    void savefile(Uploadfile uploadfile);


    @Select("select path from coap.filedata where id=#{id}")
    String selectfile(String id);

    @Select("select LAST_INSERT_ID()")
    String getLatestId();

    @Delete("Delete from coap.filedata where path=#{Url}")
    int deletefile(String Url);
}
