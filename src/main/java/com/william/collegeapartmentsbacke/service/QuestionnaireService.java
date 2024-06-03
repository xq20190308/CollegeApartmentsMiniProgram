package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireVO;

import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> selectAll();

    void deleteById(Integer id);

    void updateQuestionnaireById(long id, Questionnaire questionnaire);

    Integer simpleAdd();

    void totallyadd(Questionnaire questionnaire);
}
