package com.william.collegeapartmentsbacke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.william.collegeapartmentsbacke.mapper.SimpleAnswerMapper;
import com.william.collegeapartmentsbacke.pojo.entity.SimpleAnswer;
import com.william.collegeapartmentsbacke.service.SimpleAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/4 22:24
 * @Version: 1.0
 */
@Service
public class SimpleAnswerServiceImpl implements SimpleAnswerService {

    @Autowired
    private SimpleAnswerMapper simpleAnswerMapper;

    @Override
    public List<SimpleAnswer> getAllSimpleAnswers() {
        return simpleAnswerMapper.selectList(null);
    }

    @Override
    public List<SimpleAnswer> getByQuestionId(Integer question_id) {
        QueryWrapper<SimpleAnswer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id",question_id);
        return simpleAnswerMapper.selectList(queryWrapper);
    }

    @Override
    public List<SimpleAnswer> getByNaireId(Integer id) {
        return List.of();
    }

    @Override
    public Integer insert(SimpleAnswer simpleAnswer) {
        return 0;
    }

    @Override
    public Integer update(SimpleAnswer simpleAnswer) {
        return 0;
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }
}
