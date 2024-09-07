package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireVO;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface QuestionnaireMapper {

    @Select("select * from coap.questionnaire where is_active = '1'")
    List<Questionnaire> selectAll();

    List<QuestionnaireVO> countById(@Param("list") List<String> idList);

    @Delete("delete from coap.questionnaire where id = #{id}")
    void deleteById(Integer id);

    @Update("update coap.questionnaire set anonymous = #{anonymous}, " +
            "type = #{type}, " +
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

    //根据naireId修改问卷状态
    @Update("update coap.questionnaire set is_active = #{isActive} where id = #{id}")
    void updateIsActive(@Param("id") Integer naireId, @Param("isActive") String isActive);
}
