package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;


@Mapper
public interface FileMapper {

    @Update("insert into  coap.filedata (user_id,name,type,path,data) values(#{userid},#{name},#{type},#{path},#{data})")
    void savefile(Uploadfile file);

    @Select("select path from coap.filedata where id=#{id}")
    String selectfile(String id);

    @Select("select LAST_INSERT_ID()")
    String getLatestId();

    @Delete("Delete from coap.filedata where path=#{Url}")
    int deletefile(String Url);
}
