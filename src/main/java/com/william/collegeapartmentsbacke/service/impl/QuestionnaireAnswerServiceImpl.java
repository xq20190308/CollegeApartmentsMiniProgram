package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.QuestionnaireAnswerMapper;
import com.william.collegeapartmentsbacke.pojo.dto.AnswerDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import com.william.collegeapartmentsbacke.service.QuestionnaireAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService {

    @Autowired
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;


    //插入的同时返回插入的id
    @Override
    public Integer addQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer) {
        questionnaireAnswer.setSubmitTime(LocalDateTime.now().now());
        questionnaireAnswerMapper.insertSelective(questionnaireAnswer);
        Integer ansId = questionnaireAnswerMapper.getNewestId();

        return ansId;
    }

    @Override
    public QuestionnaireAnswer getAnswerByUseridAndNaireId(String userid, Integer naireId) {
        QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerMapper.getNaireByUseridAndNaireId(userid,naireId);
        return questionnaireAnswer;
    }

    /**
     * @param naireid
     */
    @Override
    public void answerSummery(Integer naireid) {
        List<AnswerDTO> answers = questionnaireAnswerMapper.getAnswerByNaireId(naireid);
        log.info("用户回答如下");
        for(AnswerDTO answer : answers) {
            String answerText = answer.getAnswer();
            log.info(answerText);
        }

    }
}
