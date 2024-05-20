package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Question;
import java.util.List;



public interface QuestionService {

    List<Question> selectById(List<String> idList);

    void add(String question);

    void deletByQuestionnaire(String questionnaire);

}
