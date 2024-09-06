package com.william.collegeapartmentsbacke.service.impl;

import cn.hutool.json.JSONArray;
import com.william.collegeapartmentsbacke.mapper.AnswerStatisticsMapper;
import com.william.collegeapartmentsbacke.mapper.QuestionnaireAnswerMapper;
import com.william.collegeapartmentsbacke.pojo.dto.AnswerCountDTO;
import com.william.collegeapartmentsbacke.pojo.dto.AnswerDTO;
import com.william.collegeapartmentsbacke.pojo.entity.AnswerCount;
import com.william.collegeapartmentsbacke.pojo.entity.AnswerStatistics;
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
    @Autowired
    private AnswerStatisticsMapper answerStatisticsMapper;


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
        //存储问题回答情况统计
        List<AnswerCount> answerCountList = new ArrayList<>();

        //初始化问题回答情况，将每个单选题，多选题每个选项的回答人数初始化为0
        for(Question question : questionList) {
            log.info(question.toString());
            AnswerCount answerCount = new AnswerCount();
            //问答题处理办法
            answerCount.setAnswerType(question.getType());
            if(question.getType() == "3")
            {
                ;
            }
            //选择题处理办法
            else {
                //添加每个问题的选项数，默认每个选项0个人选
                //先将问题的content转换为数组
                JSONArray jsonArray = new JSONArray(question.getContent());
                List<Integer> choiceList = new ArrayList<>();
                for(int i = 0; i < jsonArray.size();i++){
                    choiceList.add(0);
                }
                answerCount.setChoiceSumList(choiceList);
            }
            answerCount.setQuestionId(question.getId());
            answerCountList.add(answerCount);
        }

        //输出查看初始化情况
        log.info(answerCountList.toString());
        //接下来统计每个问题的回答情况

        for(int i = 0; i < answerCountList.size(); i++)
        {
            //取出每个人第i个问题的回答，统计
            AnswerCount currAnswerCount = answerCountList.get(i);
            if(currAnswerCount.getAnswerType().equals("3"))
                continue;
            else if(currAnswerCount.getAnswerType().equals("2"))
            {
                for (AnswerDTO answerDTO : answerDTOList) {
                    JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
                    Object mulQuestionIAnswer = choiceArray.get(i);

                    if (mulQuestionIAnswer instanceof JSONArray) {
                        for (Object obj : (JSONArray) mulQuestionIAnswer) {
                            if (obj instanceof String) {
                                String choiceStr = (String) obj;
                                Integer  choiceInt= Integer.parseInt(choiceStr);
                                currAnswerCount.incrementChoiceAtIndex(choiceInt);
                            }
                        }
                    }
                }
            }
            else if(currAnswerCount.getAnswerType().equals("1"))//单选题处理
            {

                for (AnswerDTO answerDTO : answerDTOList) {
                    JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
                    Object questionIAnswer = choiceArray.get(i);


                    if (questionIAnswer instanceof String) {
                        String choiceStr = (String) questionIAnswer;
                        Integer  choiceInt= Integer.parseInt(choiceStr);
                        currAnswerCount.incrementChoiceAtIndex(choiceInt);
                    }
                }
            }
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

//        log.info("往数据库存储的统计如下");
        List<AnswerStatistics> answerStatisticsList = new ArrayList<>();
        for(AnswerCount answerCount : answerCountList) {
            //跳过问答题
            if (answerCount.getAnswerType().equals("3"))
                continue;
            AnswerStatistics answerStatistics = new AnswerStatistics();
            answerStatistics.setNaireId(naireid);
            answerStatistics.setQuestionId(answerCount.getQuestionId());
            answerStatistics.setAnswerType(answerCount.getAnswerType());
            StringBuffer sb = new StringBuffer();
            for (Integer choiceCount : answerCount.getChoiceSumList()) {
                // 如果不是第一个元素，则添加逗号作为分隔符
                if (sb.length() > 0) {
                    sb.append(",");
                }
                // 将当前计数添加到 StringBuilder 中
                sb.append(choiceCount);
            }
            answerStatistics.setChoiceCount(sb.toString());
            answerStatisticsList.add(answerStatistics);
        }
//        log.info(answerStatisticsList.toString());
        answerStatisticsMapper.batchInsert(answerStatisticsList);

        //生成返回给前端的数据
        answerCountDTO.setNumOfAnswers(answerDTOList.size());
        answerCountDTO.setAnswerCountList(answerCountList);
        return answerCountDTO;
    }

}
