package com.william.collegeapartmentsbacke.service.questionnaire;

import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.Question;

import java.util.List;



public interface QuestionService {

    Question selectQuestionById(Integer questionId);

    List<Question> selectByQuestionnaireId(Integer questionnaireId);

    void deleteByQuestionnaireId(Integer questionnaireId);

    void deleteByQuestionId(Integer questionId);

    List<Integer> addQuestions(List<Question> questionList,Integer questionnaireId);
}
