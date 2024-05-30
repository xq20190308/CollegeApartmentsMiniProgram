package com.william.collegeapartmentsbacke.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.william.collegeapartmentsbacke.mapper.QuestionMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.service.QuestionService;
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
    public List<Question> selectById(List<String> idList) {
        return questionMapper.selectById(idList);
    }

    @Override
    public void deletByQuestionnaire(Integer quesionnaireId) {
        questionMapper.deleteByQuestionnaire(quesionnaireId);
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
