package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;


@Mapper
public interface FileMapper {

    @Update("insert into  coap.Filedata (id,user_id,name,Type,Path,data) values(#{id},#{userid},#{name},#{Type},#{Path},#{data})")
    void savefile(Uploadfile file);

    @Select("select path from coap.Filedata where id=#{id}")
    String selectfile(String id);

    @Select("select LAST_INSERT_ID()")
    String getLatestId();

}
