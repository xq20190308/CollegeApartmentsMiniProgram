package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.dto.QuestionnaireDTO;
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
    @RequestMapping(value = "/countByIdList", method = RequestMethod.POST)
    public Result countById(@RequestParam("idList") List<String> idList) {
        return Result.success(questionnaireService.countById(idList));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody QuestionnaireDTO questionnaire) {
        questionnaireService.add(questionnaire);
        questionService.add(questionnaire.getQuestion());
        return Result.success();
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public Result deleteById(@RequestParam("id") String id) {
        questionnaireService.deleteById(id);
        questionService.deletByQuestionnaire(id);
        return Result.success();
    }

}
