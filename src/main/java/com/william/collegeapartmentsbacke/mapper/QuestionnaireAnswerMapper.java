package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.dto.AnswerDTO;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface QuestionnaireAnswerMapper {


    @Insert("insert into coap.questionnaire_answer (answer, submit_time, questionnaire_id, userid)" +
            "values (#{answer}, #{submitTime},#{questionnaireId},#{userid})")
    void insertSelective(QuestionnaireAnswer questionnaireAnswer);



    @Select("select LAST_INSERT_id()")
    Integer getNewestId();

    @Select("select answer from coap.questionnaire_answer where (userid = #{userid} and questionnaire_id = #{naireId})")
    String getAnswerByUseridAndNaireId(String userid, Integer naireId);

    @Select("select id,answer,submit_time,questionnaire_id,userid from coap.questionnaire_answer where questionnaire_id = #{naireId}  and userid = #{userid}")
    QuestionnaireAnswer getNaireByUseridAndNaireId(String userid, Integer naireId);



    @Select("select answer from coap.questionnaire_answer where questionnaire_id = #{naireId} ")
    List<AnswerDTO> getAnswerByNaireId(Integer naireId);

    @Select("select answer from coap.questionnaire_answer where id = #{id} ")
    AnswerDTO getAnswerById(Integer id);
}
