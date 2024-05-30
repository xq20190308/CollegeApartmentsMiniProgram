package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface QuestionnaireAnswerMapper {


    @Insert("insert into coap.questionnaire_answer (answer, submit_time, questionnaire_id, user_id)" +
            "values (#{answer}, #{submitTime},#{questionnaireId},#{userid})")
    void insertSelective(QuestionnaireAnswer questionnaireAnswer);



    @Select("select LAST_INSERT_id()")
    Integer getNewestId();
}
