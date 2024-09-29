package com.william.collegeapartmentsbacke.service.questionnaire;

import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.SimpleAnswer;

import java.util.List;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/4 22:21
 * @Version: 1.0
 */
public interface SimpleAnswerService {
    //获取所有
    List<SimpleAnswer> getAllSimpleAnswers();

    //根据问题id获取
    List<SimpleAnswer> getByQuestionId(Integer id);

    //根据问卷id获取
    List<SimpleAnswer> getByNaireId(Integer id);

    //插入
    Integer insert(SimpleAnswer simpleAnswer);

    //更新
    Integer update(SimpleAnswer simpleAnswer);

    //删除
    Integer delete(Integer id);
}
