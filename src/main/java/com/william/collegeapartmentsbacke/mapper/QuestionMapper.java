package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface QuestionMapper {

    List<Question> selectById(@Param("list") List<String> idList);

    @Insert("insert into coap.question (id, type, name, describe, content, questionnaire)" +
            "values (#{id}, #{type}, #{name}, #{descr}, #{content}, #{questionnaire})")
    void add(Question question);

    @Delete("delete from coap.question where questionnaire = #{questionnaire}")
    void deleteByQuestionnaire(String questionnaire);
}
