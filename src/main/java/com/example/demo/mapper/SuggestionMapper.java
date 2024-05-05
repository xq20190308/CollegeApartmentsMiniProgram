package com.example.demo.mapper;

import com.example.demo.entity.Suggestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SuggestionMapper {
   @Select("select * from Suggesitions.Advise")
   List<Suggestion> findall();


   @Update("insert into Suggesitions.Advise (id,describes,category,pushtime,contactobject) values(#{id},#{describes},#{category},#{pushtime},#{contactobject});")
   @Transactional
   void save(Suggestion suggestion);


   @Delete("delete from Suggesitions.Advise where id=#{id}")
   void delete(long id);

   
}
