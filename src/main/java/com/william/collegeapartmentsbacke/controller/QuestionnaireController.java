package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.dto.QuestionnaireDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireVO;
import com.william.collegeapartmentsbacke.service.QuestionService;
import com.william.collegeapartmentsbacke.service.QuestionnaireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    Result selectAll() {
        log.info("查询所有问卷");
        List<Questionnaire> questionnaireList = questionnaireService.selectAll();
        return Result.success(questionnaireList);
    }
    /*
     * 统计 问卷 填写数量
     * @Param 问卷ID数组
     * */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody QuestionnaireDTO questionnaireDto) {
        log.info("添加问卷");
        Integer questionnaireId = questionnaireService.simpleAdd();
        List<Question> questionList = questionnaireDto.getQuestionList();
        questionService.addQuestions(questionList,questionnaireId);

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(questionnaireId);
        questionnaire.setType(questionnaireDto.getType());
        questionnaire.setName(questionnaireDto.getName());
        questionnaire.setDescription(questionnaireDto.getDescription());
        questionnaire.setStartTime(questionnaireDto.getStartTime());
        questionnaire.setEndTime(questionnaireDto.getEndTime());


        questionnaireService.totallyadd(questionnaire);
        log.info(questionnaire.toString());
        return Result.success();
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("id") Integer id) {
        log.info("根据问卷id删除对应问卷及其所有问题");
        questionnaireService.deleteById(id);
        questionService.deleteByQuestionnaireId(id);
        return Result.success();
    }



}
