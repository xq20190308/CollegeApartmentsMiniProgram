package com.william.collegeapartmentsbacke.controller;


import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireAwswerVO;
import com.william.collegeapartmentsbacke.service.QuestionnaireAnswerService;
import com.william.collegeapartmentsbacke.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/useranswer")
public class QuestionnaireAnswerController {

    @Autowired
    private QuestionnaireAnswerService questionnaireAnswerService;

    @Autowired
    private UserService userService;

    //提交答卷
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result addQuestionnaireAnswer(@RequestHeader("Authorization") String token ,
                                         @RequestBody QuestionnaireAnswer questionnaireAnswer) {
        log.info("token: {}",token);
        String userid = userService.getUseridFromToken(token);
        Integer naireId = questionnaireAnswer.getQuestionnaireId();
        log.info("问卷回答 userid:{}",userid);
        log.info("新增了一份问卷回答：questionnaireAnswer : {}", questionnaireAnswer);

        QuestionnaireAnswer testAnswer = questionnaireAnswerService.getAnswerByUseridAndNaireId(userid,naireId);
        if(testAnswer != null) {
            return Result.error("您已填写过该问卷");
        }


        questionnaireAnswer.setUserid(userid);
        Integer id =  questionnaireAnswerService.addQuestionnaireAnswer(questionnaireAnswer);
        log.info(id.toString());
        return Result.success();
    }

    //查询每个用户的答卷
    @RequestMapping(value = "/getmyanswer", method = RequestMethod.GET)
    public Result findQuestionnaireAnswer(@RequestHeader("Authorization") String token, @RequestParam Integer questionnaireId) {
        String userid = userService.getUseridFromToken(token);
        QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerService.getAnswerByUseridAndNaireId(userid,questionnaireId);
        //如果根据问卷id和userid查询不到填写情况
        if(questionnaireAnswer == null){
            return Result.error("您还没有填写该问卷");
        }
        log.info(questionnaireAnswer.toString());
        QuestionnaireAwswerVO questionnaireAwswerVO = QuestionnaireAwswerVO.builder()
                .answer(questionnaireAnswer.getAnswer())
                .build();
        return Result.success(questionnaireAwswerVO);
    }

    @RequestMapping(value = "/anssum",method = RequestMethod.GET)
    public Result AnsSum(@RequestHeader("Authorization") String token) {
        questionnaireAnswerService.answerSummery(108);
        return Result.success();
    }



}
