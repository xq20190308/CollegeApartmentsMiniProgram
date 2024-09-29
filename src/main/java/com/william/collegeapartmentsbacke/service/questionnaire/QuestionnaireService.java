package com.william.collegeapartmentsbacke.service.questionnaire;

import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> selectAll();

    void deleteById(Integer id);

    Integer simpleAdd();

    void totallyadd(Questionnaire questionnaire);
}
