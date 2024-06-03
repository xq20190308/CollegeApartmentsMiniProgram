package com.william.collegeapartmentsbacke.controller;


import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /*
     * 查询 题目
     * @Param 问题ID数组
     * */
    @RequestMapping(value = "/selectByQuestionId", method = RequestMethod.GET)
    public Result selectQuestionById(@RequestParam("id") Integer questionId) {
        log.info("根据id查询问题");
        Question questionList =  questionService.selectQuestionById(questionId);
        return Result.success(questionList);
    }

    @RequestMapping(value = "/selectByQuestionnaireId/{id}",method = RequestMethod.GET)
    public Result selectByQuestionnaireId(@PathVariable("id") Integer questionnaireId) {
        log.info("根据问卷id查询其所有问题");
        List<Question> questionList = questionService.selectByQuestionnaireId(questionnaireId);
        return Result.success(questionList);
    }


    @RequestMapping(value = "/deleteByQuestionId/{id}", method = RequestMethod.DELETE)
    public Result deleteByQuestionId(@PathVariable("id") Integer questionId) {
        log.info("根据问题id删除对应一个问题");
        questionService.deleteByQuestionId(questionId);
        return Result.success();
    }
}