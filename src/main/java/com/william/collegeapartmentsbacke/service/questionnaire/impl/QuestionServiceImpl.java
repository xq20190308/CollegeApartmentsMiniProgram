package com.william.collegeapartmentsbacke.service.questionnaire.impl;

import com.william.collegeapartmentsbacke.mapper.QuestionMapper;
import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.Question;
import com.william.collegeapartmentsbacke.service.questionnaire.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    public QuestionMapper questionMapper;

    @Override
    public Question selectQuestionById(Integer questionId) {
        return questionMapper.selectByQuestionId(questionId);
    }

    @Override
    public List<Question> selectByQuestionnaireId(Integer questionnaireId) {
        return questionMapper.selectByQuestionnaireId(questionnaireId);
    }

    @Override
    public void deleteByQuestionnaireId(Integer quesionnaireId) {
        questionMapper.deleteByQuestionnaireId(quesionnaireId);
    }

    @Override
    public void deleteByQuestionId(Integer questionId){
        questionMapper.deleteByQuestionId(questionId);
    }

    @Override
    public List<Integer> addQuestions(List<Question> questionList,Integer questionnaireId) {
        List<Integer> idList = new ArrayList<>();
        for (Question question : questionList) {
            question.setQuestionnaireId(questionnaireId);
            Integer id = addQuestion(question);
            idList.add(id);
        }
        return idList;
    }

    @Transactional
    Integer addQuestion(Question question) {
        questionMapper.addQuestion(question);
        Integer newid = questionMapper.getNewestId();
        return newid;
    }

}
