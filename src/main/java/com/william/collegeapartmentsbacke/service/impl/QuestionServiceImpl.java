package com.william.collegeapartmentsbacke.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.william.collegeapartmentsbacke.mapper.QuestionMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public void add(String question) {
        // JSON转换为数组
        JSONArray jsonArray = JSONUtil.parseArray(question);
        jsonArray.forEach(item -> {
            // 获取 题目对象
            JSONObject jsonObject = (JSONObject)item;
            questionMapper.add(new Question(jsonObject.getStr("id"),
                    jsonObject.getInt("type"),
                    jsonObject.getStr("name"),
                    jsonObject.getStr("descr"),
                    jsonObject.getStr("content"),
                    jsonObject.getStr("questionnaire")
            ));
        });
    }

    @Override
    public void deletByQuestionnaire(String quesionnaire) {
        questionMapper.deleteByQuestionnaire(quesionnaire);
    }

}
