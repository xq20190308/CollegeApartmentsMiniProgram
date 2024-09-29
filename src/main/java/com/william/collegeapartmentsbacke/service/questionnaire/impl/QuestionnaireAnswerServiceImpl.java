package com.william.collegeapartmentsbacke.service.questionnaire.impl;

import cn.hutool.json.JSONArray;
import com.william.collegeapartmentsbacke.mapper.AnswerStatisticsMapper;
import com.william.collegeapartmentsbacke.mapper.QuestionMapper;
import com.william.collegeapartmentsbacke.mapper.QuestionnaireAnswerMapper;
import com.william.collegeapartmentsbacke.mapper.SimpleAnswerMapper;
import com.william.collegeapartmentsbacke.pojo.dto.questionnaire.AnswerCountDTO;
import com.william.collegeapartmentsbacke.pojo.dto.questionnaire.AnswerDTO;
import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.*;
import com.william.collegeapartmentsbacke.service.questionnaire.QuestionnaireAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService {

    @Autowired
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private AnswerStatisticsMapper answerStatisticsMapper;
    @Autowired
    private SimpleAnswerMapper simpleAnswerMapper;


    //插入的同时返回插入的id
    @Transactional
    protected Integer addQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer) {
        questionnaireAnswer.setSubmitTime(LocalDateTime.now().now());
        questionnaireAnswerMapper.insertSelective(questionnaireAnswer);
        Integer ansId = questionnaireAnswerMapper.getNewestId();
//        answerSummery(questionnaireAnswer.getQuestionnaireId());
        return ansId;
    }

    @Override
    @Transactional
    public Integer addAnswerAndCount(QuestionnaireAnswer questionnaireAnswer, String userid) {
        //将当前回答插入数据库并得到ansId
        Integer ansId = addQuestionnaireAnswer(questionnaireAnswer);
        Integer naireId = questionnaireAnswer.getQuestionnaireId();

        //得到当前提交回答的数据
        AnswerDTO answerDTO = questionnaireAnswerMapper.getAnswerById(ansId);
        log.info(answerDTO.toString());

        //对数据库中的数据进行解析
        List<AnswerCount> answerCountListBefore = parseAnswerCountsFromStatistics(naireId);
        //如果之前没有统计过，则创建新的统计，初始化每个选项为0
        if (answerCountListBefore.isEmpty()) {
            answerCountListBefore = initAnsCntList(questionMapper.selectByQuestionnaireId(naireId));
        }
        log.info("anserCntBefore" + answerCountListBefore);
        //对本次回答进行解析，统计每个选项的数量
        List<AnswerCount> curAnswerCountList = praseAnswerAndSum(answerDTO, answerCountListBefore, userid, naireId);
        log.info("anserCntNow" + curAnswerCountList);
        answerStatisticsMapper.batchInsert(transferCountToStaticistics(curAnswerCountList, naireId));
        return ansId;

    }

    @Override
    public AnswerCountDTO answerSummery(Integer naireId) {

        //对数据库中的数据进行解析
        List<AnswerCount> answerCountListBefore = parseAnswerCountsFromStatistics(naireId);
        //如果之前没有统计过，则创建新的统计，初始化每个选项为0
        if (answerCountListBefore.isEmpty()) {
            log.info("answerCountListBefore is empty");
            answerCountListBefore = initAnsCntList(questionMapper.selectByQuestionnaireId(naireId));
        }
        log.info("anserCntBefore" + answerCountListBefore);
        Integer numOfAnswers = questionnaireAnswerMapper.getAnswerCountByNaireId(naireId);
        log.info("答题人数" + numOfAnswers);

        AnswerCountDTO answerCountDTO = new AnswerCountDTO();
        answerCountDTO.setNumOfAnswers(numOfAnswers);
        answerCountDTO.setAnswerCountList(answerCountListBefore);
        log.info(answerCountDTO.toString());
        return answerCountDTO;

//
//        AnswerCountDTO answerCountDTO = new AnswerCountDTO();
//        //从数据库取的回答情况List
//        List<AnswerDTO> answerDTOList = questionnaireAnswerMapper.getAnswerByNaireId(naireid);
//        //存储问题列表
//        List<Question> questionList = questionMapper.selectByQuestionnaireId(naireid);
//        //初始化：存储问题回答情况统计
//        List<AnswerCount> answerCountList = initAnsCntList(questionList);
//        //输出查看初始化情况
//        log.info(answerCountList.toString());
//        //接下来统计每个问题的回答情况
//
//        for (int i = 0; i < answerCountList.size(); i++) {
//            //取出每个人第i个问题的回答，统计
//            AnswerCount currAnswerCount = answerCountList.get(i);
//            if (currAnswerCount.getAnswerType() == 3)
//                continue;
//            else if (currAnswerCount.getAnswerType() == 2) {
//                for (AnswerDTO answerDTO : answerDTOList) {
//                    JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
//                    Object mulQuestionIAnswer = choiceArray.get(i);
//
//                    if (mulQuestionIAnswer instanceof JSONArray) {
//                        for (Object obj : (JSONArray) mulQuestionIAnswer) {
//                            if (obj instanceof String) {
//                                String choiceStr = (String) obj;
//                                Integer choiceInt = Integer.parseInt(choiceStr);
//                                currAnswerCount.incrementChoiceAtIndex(choiceInt);
//                            }
//                        }
//                    }
//                }
//            } else if (currAnswerCount.getAnswerType() == 1)//单选题处理
//            {
//
//                for (AnswerDTO answerDTO : answerDTOList) {
//                    JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
//                    Object questionIAnswer = choiceArray.get(i);
//
//
//                    if (questionIAnswer instanceof String) {
//                        String choiceStr = (String) questionIAnswer;
//                        Integer choiceInt = Integer.parseInt(choiceStr);
//                        currAnswerCount.incrementChoiceAtIndex(choiceInt);
//                    }
//                }
//            }
//        }
//
//        log.info("用户回答如下");
//        for (AnswerDTO answer : answerDTOList) {
//            String answerText = answer.getAnswer();
//            log.info(answerText);
//        }
//
//        log.info("统计如下：");
//        for (AnswerCount answerCount : answerCountList) {
//            log.info(answerCount.toString());
//        }
//
////        log.info("往数据库存储的统计如下");
//        List<AnswerStatistics> answerStatisticsList = new ArrayList<>();
//        for (AnswerCount answerCount : answerCountList) {
//            //跳过问答题
//            if (answerCount.getAnswerType() == 3)
//                continue;
//            AnswerStatistics answerStatistics = new AnswerStatistics();
//            answerStatistics.setNaireId(naireid);
//            answerStatistics.setQuestionId(answerCount.getQuestionId());
//            answerStatistics.setAnswerType(answerCount.getAnswerType());
//            StringBuffer sb = new StringBuffer();
//            for (Integer choiceCount : answerCount.getChoiceSumList()) {
//                // 如果不是第一个元素，则添加逗号作为分隔符
//                if (sb.length() > 0) {
//                    sb.append(",");
//                }
//                // 将当前计数添加到 StringBuilder 中
//                sb.append(choiceCount);
//            }
//            answerStatistics.setChoiceCount(sb.toString());
//            answerStatisticsList.add(answerStatistics);
//        }
////        log.info(answerStatisticsList.toString());
//        answerStatisticsMapper.batchInsert(answerStatisticsList);
//
//        //生成返回给前端的数据
//        answerCountDTO.setNumOfAnswers(answerDTOList.size());
//        answerCountDTO.setAnswerCountList(answerCountList);
//        return answerCountDTO;
    }


    @Override
    public QuestionnaireAnswer getAnswerByUseridAndNaireId(String userid, Integer naireId) {
        QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerMapper.getNaireByUseridAndNaireId(userid, naireId);
        return questionnaireAnswer;
    }

    /**
     * @param naireId
     */


    @Override
    public void deleteAnswer(Integer naireId) {
        questionnaireAnswerMapper.deleteAnswerByNaireId(naireId);
    }


    private List<AnswerStatistics> transferCountToStaticistics(List<AnswerCount> answerCountList, Integer naireId) {
        List<AnswerStatistics> answerStatisticsList = new ArrayList<>();
        for (AnswerCount answerCount : answerCountList) {
            //跳过问答题
            if (answerCount.getAnswerType().equals("3"))
                continue;
            AnswerStatistics answerStatistics = new AnswerStatistics();
            answerStatistics.setNaireId(naireId);
            answerStatistics.setQuestionId(answerCount.getQuestionId());
            answerStatistics.setAnswerType(answerCount.getAnswerType());
            StringBuffer sb = new StringBuffer();
            for (String choiceCount : answerCount.getChoiceSumList()) {
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
        return answerStatisticsList;
    }


    /**
     * 根据问卷id获取问卷的回答情况
     *
     * @param questionList 问卷问题列表
     * @return 问卷回答情况
     */
    private List<AnswerCount> initAnsCntList(List<Question> questionList) {
        //存储问题回答情况统计
        List<AnswerCount> answerCountList = new ArrayList<>();

        //根据文问卷中的问题，初始化问题回答情况，将每个单选题，多选题每个选项的回答人数初始化为0
        for (Question question : questionList) {
            log.info(question.toString());
            AnswerCount answerCount = new AnswerCount();
            //问答题处理办法
            answerCount.setAnswerType(question.getType());
            if (question.getType().equals("3")) {
                answerCount.setChoiceSumList(new ArrayList<>());
            }
            //选择题处理办法
            else {
                //添加每个问题的选项数，默认每个选项0个人选
                //先将问题的content转换为数组
                JSONArray jsonArray = new JSONArray(question.getContent());
                List<String> choiceList = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    choiceList.add("0");
                }
                answerCount.setChoiceSumList(choiceList);
            }
            answerCount.setQuestionId(question.getId());
            answerCountList.add(answerCount);
        }
        return answerCountList;
    }


    private List<AnswerCount> parseAnswerCountsFromStatistics(Integer naireId) {
        // 非空检查，防止 NullPointerException
        List<AnswerStatistics> answerStatisticList = answerStatisticsMapper.selectByNaireId(naireId);
        if (answerStatisticList == null) {
            log.warn("answerStatisticsMapper.selectByNaireId({}) returned null.", naireId);
            return Collections.emptyList();
        }

        log.info("Retrieved {} answer statistics records for naireId: {}", answerStatisticList.size(), naireId);

        if (answerStatisticList.isEmpty()) {
            return Collections.emptyList();
        }

        List<AnswerCount> answerCountList = new ArrayList<>();
        for (AnswerStatistics answerStatistic : answerStatisticList) {
            AnswerCount answerCount = new AnswerCount();
            answerCount.setAnswerType(answerStatistic.getAnswerType());
            answerCount.setChoiceSumList(parseAnswerStatisticChoiceCount(answerStatistic));
            answerCount.setQuestionId(answerStatistic.getQuestionId());
            answerCountList.add(answerCount);
        }

        log.info("Parsed {} answer count records for naireId: {}", answerCountList.size(), naireId);
        return answerCountList;
    }


    private List<String> parseAnswerStatisticChoiceCount(AnswerStatistics answerStatistic) {

        String choiceCountStr = answerStatistic.getChoiceCount();
        String[] choiceCountArray = choiceCountStr.split(",");
        List<String> choiceCountList = new ArrayList<>();
        for (String choiceCount : choiceCountArray) {
            choiceCountList.add(choiceCount);
        }
        return choiceCountList;
    }


    /**
     * 解析答案并汇总
     * 该方法根据用户的回答情况，对答案进行统计和存储
     * 主要处理三种类型的题目：简答题、多选题和单选题
     *
     * @param answerDTO      用户的答案对象，包含用户的所有回答信息
     * @param answerCountList 问题答案统计列表，每个元素对应一个问题的答案统计情况
     * @param userid         用户ID，用于记录回答问题的用户
     * @param naireId        问卷ID，表示当前答案所属的问卷
     * @return 返回更新后的答案统计列表
     */
    private List<AnswerCount> praseAnswerAndSum(AnswerDTO answerDTO, List<AnswerCount> answerCountList, String userid, Integer naireId) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        // 遍历每个问题的答案统计对象
        for (int i = 0; i < answerCountList.size(); i++) {
            AnswerCount currAnswerCount = answerCountList.get(i);

            // 记录当前正在处理的问题编号
            logger.info("开始处理问题：{}", i + 1);

            // 处理简答题
            if (currAnswerCount.getAnswerType().equals("3")) {
                JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
                Object simpleQuesAnswerObj = choiceArray.get(i);

                if (simpleQuesAnswerObj instanceof String) {
                    String simpleQuesAnsStr = (String) simpleQuesAnswerObj;
                    currAnswerCount.getChoiceSumList().add(simpleQuesAnsStr);

                    SimpleAnswer simpAnswer = new SimpleAnswer();
                    simpAnswer.setNaireId(naireId);
                    simpAnswer.setQuestionId(currAnswerCount.getQuestionId());
                    simpAnswer.setUserid(userid);
                    simpAnswer.setAnswer(simpleQuesAnsStr);
                    simpleAnswerMapper.insert(simpAnswer);

                    logger.info("已记录简答题答案：{}", simpleQuesAnsStr);
                }

                // 处理多选题
            } else if (currAnswerCount.getAnswerType().equals("2")) {
                JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
                Object mulQuestionIAnswer = choiceArray.get(i);

                if (mulQuestionIAnswer instanceof JSONArray) {
                    for (Object obj : (JSONArray) mulQuestionIAnswer) {
                        if (obj instanceof Integer) {
//                            String choiceStr = (String) obj;
//                            Integer choiceInt = Integer.parseInt(choiceStr);
                            Integer choiceInt = (Integer) obj;
                            currAnswerCount.incrementChoiceAtIndex(choiceInt);
                            logger.info("已增加多选题选项：{}", choiceInt);
                        }
                    }
                }
                // 处理单选题
            } else if (currAnswerCount.getAnswerType().equals("1")) {
                JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
                Object questionIAnswer = choiceArray.get(i);

                if (questionIAnswer instanceof Integer) {
//                    String choiceStr = (String) questionIAnswer;
//                    Integer choiceInt = Integer.parseInt(choiceStr);
                    Integer choiceInt = (Integer) questionIAnswer;
                    currAnswerCount.incrementChoiceAtIndex(choiceInt);
                    logger.info("已增加单选题选项：{}", choiceInt);
                }
            }
        }

        // 返回处理后的答案统计列表
        return answerCountList;
    }


}

//    private List<AnswerCount> praseAnswerAndSum(AnswerDTO answerDTO, List<AnswerCount> answerCountList,String userid, Integer naireId) {
//
//        for (int i = 0; i < answerCountList.size(); i++) {
//            //取出每个人第i个问题的回答，统计
//            AnswerCount currAnswerCount = answerCountList.get(i);
//            if (currAnswerCount.getAnswerType().equals("3")){
//                JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
//                Object simpleQuesAnswerObj = choiceArray.get(i);
//                if (simpleQuesAnswerObj instanceof String) {
//                    String simpleQuesAnsStr = (String) simpleQuesAnswerObj;
//                    currAnswerCount.getChoiceSumList().add(simpleQuesAnsStr);
//                    SimpleAnswer simpAnswer = new SimpleAnswer();
//                    simpAnswer.setNaireId(naireId);
//                    simpAnswer.setQuestionId(currAnswerCount.getQuestionId());
//                    simpAnswer.setUserid(userid);
//                    simpAnswer.setAnswer(simpleQuesAnsStr);
//                    simpleAnswerMapper.insert(simpAnswer);
//                }
//
//            }
//            else if (currAnswerCount.getAnswerType().equals("2")) {
//                    JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
//                    Object mulQuestionIAnswer = choiceArray.get(i);
//
//                    if (mulQuestionIAnswer instanceof JSONArray) {
//                        for (Object obj : (JSONArray) mulQuestionIAnswer) {
//                            if (obj instanceof String) {
//                                String choiceStr = (String) obj;
//                                Integer choiceInt = Integer.parseInt(choiceStr);
//                                currAnswerCount.incrementChoiceAtIndex(choiceInt);
//                            }
//                        }
//                    }
//            } else if (currAnswerCount.getAnswerType().equals("1"))//单选题处理
//            {
//                    JSONArray choiceArray = new JSONArray(answerDTO.getAnswer());
//                    Object questionIAnswer = choiceArray.get(i);
//
//                    if (questionIAnswer instanceof String) {
//                        String choiceStr = (String) questionIAnswer;
//                        Integer choiceInt = Integer.parseInt(choiceStr);
//                        currAnswerCount.incrementChoiceAtIndex(choiceInt);
//                    }
//            }
//        }
//        return answerCountList;
//    }
//}


























