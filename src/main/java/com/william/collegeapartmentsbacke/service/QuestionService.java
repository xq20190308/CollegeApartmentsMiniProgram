package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;

import java.util.List;



public interface QuestionService {

    Question selectQuestionById(Integer questionId);

    List<Question> selectByQuestionnaireId(Integer questionnaireId);

    void deleteByQuestionnaireId(Integer questionnaireId);

    void deleteByQuestionId(Integer questionId);

    void updateQuestion(Question question);

    List<Integer> addQuestions(List<Question> questionList,Integer questionnaireId);
}
