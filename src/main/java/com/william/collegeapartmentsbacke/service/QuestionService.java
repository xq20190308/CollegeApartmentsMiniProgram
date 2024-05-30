package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;

import java.util.List;



public interface QuestionService {

    List<Question> selectById(List<String> idList);

    void deletByQuestionnaire(Integer questionnaire);

    List<Integer> addQuestions(List<Question> questionList,Integer questionnaireId);
}
