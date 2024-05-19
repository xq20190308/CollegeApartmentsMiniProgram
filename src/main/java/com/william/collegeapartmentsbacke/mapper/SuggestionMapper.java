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
   @Select("select * from Suggesitions.AdviseDraft where pushtime=#{pushtime}")
   List<Suggestion> Draftfindall(String pushtime);

   @Select("select * from Suggesitions.Advise")
   List<Suggestion> findall();

   @Update("insert into Suggesitions.Advise (id,describes,category,pushtime,contactobject) values(#{id},#{describes},#{category},#{pushtime},#{contactobject});")
   @Transactional
   void submit(Suggestion suggestion);

   @Update("insert into Suggesitions.AdviseDraft (id,describes,category,pushtime,contactobject) values(#{id},#{describes},#{category},#{pushtime},#{contactobject});")
   @Transactional
   void savedaft(Suggestion suggestion);

   @Delete("delete from Suggesitions.AdviseDraft where id=#{id}")
   void delete(long id);

   @Select("select count(*) from Suggesitions.AdviseDraft")
   int Count();

   @Update("insert into  Suggesitions.Filedata (id,name,Type,Path,data) values(#{id},#{name},#{Type},#{Path},#{data})")
   @Transactional
   void savefile(Uploadfile file);

   @Select("select path from Suggesitions.Filedata where id=#{id}")
   String selectfile(String id);

}
