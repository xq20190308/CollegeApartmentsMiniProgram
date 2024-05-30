package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.QuestionnaireAnswerMapper;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import com.william.collegeapartmentsbacke.service.QuestionnaireAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService {

    @Autowired
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;


    //插入的同时返回插入的id
    @Override
    public Integer addQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer) {
        questionnaireAnswerMapper.insertSelective(questionnaireAnswer);
        Integer ansId = questionnaireAnswerMapper.getNewestId();

        return ansId;
    }
}
