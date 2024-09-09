package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;

import java.util.List;



public interface QuestionService {

    Question selectQuestionById(Integer questionId);

    List<Question> selectByQuestionnaireId(Integer questionnaireId);

    //更新一组问题
    void updateQuestions(List<Question> questionList);

    void deleteByQuestionId(Integer questionId);

    List<Integer> addQuestions(List<Question> questionList,Integer questionnaireId);
}
