package com.william.collegeapartmentsbacke.service.impl;

import cn.hutool.json.JSONArray;
import com.william.collegeapartmentsbacke.mapper.QuestionnaireAnswerMapper;
import com.william.collegeapartmentsbacke.pojo.dto.AnswerCountDTO;
import com.william.collegeapartmentsbacke.pojo.dto.AnswerDTO;
import com.william.collegeapartmentsbacke.pojo.entity.AnswerCount;
import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import com.william.collegeapartmentsbacke.service.QuestionService;
import com.william.collegeapartmentsbacke.service.QuestionnaireAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService {

    @Autowired
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;
    @Autowired
    private QuestionService questionService;


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
    public AnswerCountDTO answerSummery(Integer naireid) {
        AnswerCountDTO answerCountDTO = new AnswerCountDTO();
        //从数据库取的回答情况List
        List<AnswerDTO> answerDTOList = questionnaireAnswerMapper.getAnswerByNaireId(naireid);
        //存储问题列表
        List<Question> questionList = questionService.selectByQuestionnaireId(naireid);
        Collections.sort(questionList);
        //存储问题回答情况统计
        List<AnswerCount> answerCountList = new ArrayList<>();
        //初始化问题回答情况，将每个单选题，多选题每个选项的回答人数初始化为0
        for(Question question : questionList) {
            if(question.getType() >= 3)//只统计单选题，多选题
                break;

            //添加每个问题的选项数，默认每个选项0个人选
            //先将问题的content转换为数组
            JSONArray jsonArray = new JSONArray(question.getContent());
            List<Integer> choiceList = new ArrayList<>();
            for(int i = 0; i < jsonArray.size();i++){
                choiceList.add(0);
            }
            AnswerCount answerCount = new AnswerCount();
            answerCount.setQuestionId(question.getId());
            answerCount.setChoiceSum(choiceList);
            answerCountList.add(answerCount);
        }
        //接下来统计每个问题的回答情况
        for (AnswerCount answerCount : answerCountList){

        }


        log.info("用户回答如下");
        for(AnswerDTO answer : answerDTOList) {
            String answerText = answer.getAnswer();
            log.info(answerText);
        }

        log.info("统计如下：");
        for(AnswerCount answerCount : answerCountList) {
            log.info(answerCount.toString());
        }
        //生成返回给前端的数据
        answerCountDTO.setNumOfAnswers(questionList.size());
        answerCountDTO.setAnswerCountList(answerCountList);
        return answerCountDTO;
    }
}
