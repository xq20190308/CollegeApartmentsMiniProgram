package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Uploadfile;
import com.william.collegeapartmentsbacke.pojo.entity.Suggestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface SuggestionMapper {
   //查询草稿
   @Select("select * from coap.AdviseDraft where stu_id=#{id} order by id desc ")
   List<Suggestion> Draftfindall(String id);


   @Select("select * from coap.AdviseDraft where id=#{id} order by id desc ")
   Boolean Draftselect(Integer id);

   //获取提交
   @Select("select * from coap.Advise")
   List<Suggestion> findall();

   //提交
   @Update("insert into coap.Advise (id,describes,category,pushtime,contactobject,path,status) values(#{id},#{describes},#{category},#{pushtime},#{contactobject},#{path},#{status});")
   @Transactional
   void submit(Suggestion suggestion);

   @Select("select LAST_INSERT_id()")
   Integer selectLast();

   //保存草稿
   @Update("insert into coap.AdviseDraft (id,stu_id,describes,category,pushtime,contactobject,path) values(#{id},#{stu_id},#{describes},#{category},#{pushtime},#{contactobject},#{path});")
   @Transactional
   void savedaft(Suggestion suggestion);

   //编辑草稿
   @Update("update coap.AdviseDraft set describes=#{describes},category=#{category},pushtime=#{pushtime},contactobject=#{contactobject},path#{path}, where id=#{id}")
   @Transactional
   void updatedaft(Suggestion suggestion);


   @Delete("delete from coap.AdviseDraft where id=#{id}")
   void delete(long id);

   @Select("select count(*) from coap.AdviseDraft")
   int Count();

   @Update("insert into  coap.Filedata (id,user_id,name,type,path,data) values(#{id},#{userid},#{name},#{type},#{path},#{data})")
   @Transactional
   void savefile(Uploadfile file);

   @Select("select path from coap.Filedata where id=#{id}")
   String selectfile(String id);

   @Update("update coap.Advise set status=#{status} where id=#{id}")
   void updataStatus(Suggestion suggestion);
}
