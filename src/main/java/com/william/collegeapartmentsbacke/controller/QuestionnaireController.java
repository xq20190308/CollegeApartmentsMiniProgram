package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.dto.AnswerCountDTO;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.dto.QuestionnaireDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.QuestionnaireAwswerVO;
import com.william.collegeapartmentsbacke.service.QuestionService;
import com.william.collegeapartmentsbacke.service.QuestionnaireAnswerService;
import com.william.collegeapartmentsbacke.service.QuestionnaireService;
import com.william.collegeapartmentsbacke.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionnaireAnswerService questionnaireAnswerService;

    @Autowired
    private UserService userService;


    //问卷部分
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    Result selectAll() {
        log.info("查询所有问卷");
        List<Questionnaire> questionnaireList = questionnaireService.selectAll();
        return Result.success(questionnaireList);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody QuestionnaireDTO questionnaireDto) {
        log.info("添加问卷");
        Integer questionnaireId = questionnaireService.simpleAdd();
        List<Question> questionList = questionnaireDto.getQuestionList();
        questionService.addQuestions(questionList,questionnaireId);

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(questionnaireId);
        questionnaire.setAnonymous(questionnaireDto.getAnonymous());
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
    @Transactional
    public Result deleteById(@PathVariable("id") Integer id) {
        log.info("根据问卷id删除对应问卷及其所有问题");
        questionnaireService.deleteById(id);
//        int i=1/0;
        questionService.deleteByQuestionnaireId(id);

        return Result.success();
    }

    @RequestMapping(value = "/updateQuestionnaireById/{id}", method = RequestMethod.POST)
    public Result updateQuestionnaireById(@PathVariable("id") Integer id,@RequestBody QuestionnaireDTO questionnaireDto) {
        log.info("根据id修改问卷");
        List<Question> newquestionList = questionnaireDto.getQuestionList();
        Questionnaire questionnaire = new Questionnaire();

        questionnaire.setId(id);
        questionnaire.setAnonymous(questionnaireDto.getAnonymous());
        questionnaire.setType(questionnaireDto.getType());
        questionnaire.setName(questionnaireDto.getName());
        questionnaire.setDescription(questionnaireDto.getDescription());
        questionnaire.setStartTime(questionnaireDto.getStartTime());
        questionnaire.setEndTime(questionnaireDto.getEndTime());

        log.info(questionnaire.toString());
        questionService.deleteByQuestionnaireId(id);
        questionService.addQuestions(newquestionList,id);
        questionnaireService.totallyadd(questionnaire);

        return Result.success(questionnaire);
    }



    //问题部分
    @RequestMapping(value = "/question/selectByQuestionId", method = RequestMethod.GET)
    public Result selectQuestionById(@RequestParam("id") Integer questionId) {
        log.info("根据id查询问题");
        Question questionList =  questionService.selectQuestionById(questionId);
        return Result.success(questionList);
    }

    @RequestMapping(value = "/question/selectByQuestionnaireId/{id}",method = RequestMethod.GET)
    public Result selectByQuestionnaireId(@PathVariable("id") Integer questionnaireId) {
        log.info("根据问卷id查询其所有问题");
        List<Question> questionList = questionService.selectByQuestionnaireId(questionnaireId);
        return Result.success(questionList);
    }


    @RequestMapping(value = "/question/deleteByQuestionId/{id}", method = RequestMethod.DELETE)
    public Result deleteByQuestionId(@PathVariable("id") Integer questionId) {
        log.info("根据问题id删除对应一个问题");
        questionService.deleteByQuestionId(questionId);
        return Result.success();
    }






    //提交问卷部分
    @RequestMapping(value = "/useranswer/submit",method = RequestMethod.POST)
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
        Integer id =  questionnaireAnswerService.addAnswerAndCount(questionnaireAnswer);
        log.info(id.toString());
        return Result.success();
    }

    //查询每个用户的答卷
    @RequestMapping(value = "/useranswer/getmyanswer", method = RequestMethod.GET)
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


    @RequestMapping(value = "/useranswer/anssum",method = RequestMethod.GET)
    public Result AnsSum(@RequestHeader("Authorization") String token, @RequestParam Integer questionnaireId) {
        AnswerCountDTO answerCountList = questionnaireAnswerService.answerSummery(questionnaireId);
        return Result.success(answerCountList);
    }
}
