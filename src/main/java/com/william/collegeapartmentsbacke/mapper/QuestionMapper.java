package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<Question> selectById(@Param("list") List<String> idList);

    @Insert("insert into coap.question (type, name, description, content, questionnaire_id)" +
            "values (#{type}, #{name}, #{description}, #{content}, #{questionnaireId})")
    void addQuestion(Question question);

    @Select("select * from coap.question where id = #{questionId}")
    Question selectByQuestionId(Integer questionId);

    @Select("select * from coap.question where questionnaire_id = #{questionnaireId}")
    List<Question> selectByQuestionnaireId(Integer questionnaireId);

    @Delete("delete from coap.question where questionnaire_id = #{questionnaireId}")
    void deleteByQuestionnaireId(Integer questionnaireId);

    @Delete("delete from coap.question where id = #{id}")
    void deleteByQuestionId(Integer id);

    @Select("select LAST_INSERT_id()")
    Integer getNewestId();

}
