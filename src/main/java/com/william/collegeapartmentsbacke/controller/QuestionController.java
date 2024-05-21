package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/selectById", method = RequestMethod.POST)
    public Result selectById(@RequestParam List<String> idList) {
        log.info("根据id查询问题");
        List<Question> questions = questionService.selectById(idList);
        return Result.success(questions);
    }

    @RequestMapping(value = "/deletByQuestionnaire", method = RequestMethod.POST)
    public Result
    deletByQuestionnaire(String questionnaire) {
        questionService.deletByQuestionnaire(questionnaire);
        return Result.success();
    }
}
