package com.william.collegeapartmentsbacke.mapper;

import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireVO;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface QuestionnaireMapper {

    @Select("select * from coap.questionnaire")
    List<Questionnaire> selectAll();

    List<QuestionnaireVO> countById(@Param("list") List<String> idList);

    @Insert("insert into coap.questionnaire (id, type, name, descr, start_time, end_time, question_list) " +
            "values (#{id}, #{type}, #{name}, #{descr}, #{startTime}, #{endTime}, #{questionList})")
    void add(Questionnaire questionnaire);

    @Select("select * from coap.questionnaire where id = #{id}")
    Questionnaire selectById(String id);

    @Delete("delete from coap.questionnaire where id = #{id}")
    void deleteById(String id);
}
