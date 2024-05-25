package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.Uploadfile;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SuggestionMapper {
   @Select("select * from coap.AdviseDraft")
   List<Suggestion> Draftfindall();

   @Select("select * from coap.Advise")
   List<Suggestion> findall();

   @Update("insert into coap.Advise (id,describes,category,pushtime,contactobject) values(#{id},#{describes},#{category},#{pushtime},#{contactobject});")
   @Transactional
   void submit(Suggestion suggestion);

   @Select("select LAST_INSERT_id()")
   Integer selectLast();

   @Update("insert into coap.AdviseDraft (id,describes,category,pushtime,contactobject) values(#{id},#{describes},#{category},#{pushtime},#{contactobject});")
   @Transactional
   void savedaft(Suggestion suggestion);

   @Delete("delete from coap.AdviseDraft where id=#{id}")
   void delete(long id);

   @Select("select count(*) from coap.AdviseDraft")
   int Count();

   @Update("insert into  coap.Filedata (id,name,Type,Path,data) values(#{id},#{name},#{Type},#{Path},#{data})")
   @Transactional
   void savefile(Uploadfile file);

   @Select("select path from coap.Filedata where id=#{id}")
   String selectfile(String id);

}
