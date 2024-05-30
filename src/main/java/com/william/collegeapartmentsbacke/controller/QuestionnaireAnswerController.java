package com.william.collegeapartmentsbacke.controller;


import cn.hutool.core.date.DateTime;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import com.william.collegeapartmentsbacke.service.QuestionnaireAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/useranswer")
public class QuestionnaireAnswerController {

    @Autowired
    QuestionnaireAnswerService questionnaireAnswerService;

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result addQuestionnaireAnswer(@RequestBody QuestionnaireAnswer questionnaireAnswer) {
        log.info("新增了一份问卷回答：questionnaireAnswer : {}", questionnaireAnswer);
        questionnaireAnswer.setUserid("11");
        questionnaireAnswer.setSubmitTime(DateTime.now());
        Integer id =  questionnaireAnswerService.addQuestionnaireAnswer(questionnaireAnswer);
        log.info(id.toString());
        return Result.success();
    }


}
