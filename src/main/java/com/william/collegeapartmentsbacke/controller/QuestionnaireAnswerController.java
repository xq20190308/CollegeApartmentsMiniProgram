package com.william.collegeapartmentsbacke.controller;


import cn.hutool.core.date.DateTime;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import com.william.collegeapartmentsbacke.service.QuestionnaireAnswerService;
import com.william.collegeapartmentsbacke.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/useranswer")
public class QuestionnaireAnswerController {

    @Autowired
    private QuestionnaireAnswerService questionnaireAnswerService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result addQuestionnaireAnswer(@RequestHeader("Authorization") String token ,
                                         @RequestBody QuestionnaireAnswer questionnaireAnswer) {
        log.info("token: {}",token);
        String userid = userService.getUseridByToken(token);
        log.info("问卷回答 userid:{}",userid);
        log.info("新增了一份问卷回答：questionnaireAnswer : {}", questionnaireAnswer);
        questionnaireAnswer.setUserid("11");
        questionnaireAnswer.setSubmitTime(DateTime.now());
        Integer id =  questionnaireAnswerService.addQuestionnaireAnswer(questionnaireAnswer);
        log.info(id.toString());
        return Result.success();
    }


}
