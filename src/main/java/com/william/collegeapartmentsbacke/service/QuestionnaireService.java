package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireVO;

import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> selectAll();

    List<QuestionnaireVO> countById(List<String> idList);

    void add(Questionnaire questionnaire);

    void deleteById(Integer id);

    Questionnaire selectById(String id);

}
