package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireVO;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface QuestionnaireMapper {

    @Select("select * from coap.questionnaire")
    List<Questionnaire> selectAll();

    List<QuestionnaireVO> countById(@Param("list") List<String> idList);

    @Select("select * from coap.questionnaire where id = #{id}")
    Questionnaire selectById(Integer id);

    @Delete("delete from coap.questionnaire where id = #{id}")
    void deleteById(Integer id);

    @Update("update coap.questionnaire set type = #{type}, " +
            "name = #{name}, " +
            "description = #{description}, " +
            "start_time = #{startTime}, " +
            "end_time = #{endTime} " +
            "where id = #{id}")
    void updateQuestionnaire(Questionnaire questionnaire);

    @Insert("insert into coap.questionnaire (type) values (1)")
    void simpleadd();

    @Select("select LAST_INSERT_id()")
    Integer getNewestId();
}
