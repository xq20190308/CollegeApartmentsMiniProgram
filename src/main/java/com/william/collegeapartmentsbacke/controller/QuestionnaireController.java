package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.dto.questionnaire.AnswerCountDTO;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.QuestionnaireAnswer;
import com.william.collegeapartmentsbacke.pojo.dto.questionnaire.QuestionnaireDTO;
import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.Question;
import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.Questionnaire;
import com.william.collegeapartmentsbacke.pojo.vo.questionnaire.QuestionnaireAwswerVO;
import com.william.collegeapartmentsbacke.service.questionnaire.QuestionService;
import com.william.collegeapartmentsbacke.service.questionnaire.QuestionnaireAnswerService;
import com.william.collegeapartmentsbacke.service.questionnaire.QuestionnaireService;
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
    public AjaxResult selectAll() {
        log.info("查询所有问卷");
        List<Questionnaire> questionnaireList = questionnaireService.selectAll();
        return AjaxResult.success(questionnaireList);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxResult add(@RequestBody QuestionnaireDTO questionnaireDto) {
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
        return AjaxResult.success();
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    @Transactional
    public AjaxResult deleteById(@PathVariable("id") Integer id) {
        log.info("根据问卷id删除对应问卷及其所有问题");
        questionnaireService.deleteById(id);
//        int i=1/0;
//        questionService.deleteByQuestionnaireId(id);

        return AjaxResult.success();
    }

    @RequestMapping(value = "/updateQuestionnaireById/{id}", method = RequestMethod.POST)
    public AjaxResult updateQuestionnaireById(@PathVariable("id") Integer id,@RequestBody QuestionnaireDTO questionnaireDto) {
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

        return AjaxResult.success(questionnaire);
    }



    //问题部分
    @RequestMapping(value = "/question/selectByQuestionId", method = RequestMethod.GET)
    public AjaxResult selectQuestionById(@RequestParam("id") Integer questionId) {
        log.info("根据id查询问题");
        Question questionList =  questionService.selectQuestionById(questionId);
        return AjaxResult.success(questionList);
    }

    @RequestMapping(value = "/question/selectByQuestionnaireId/{id}",method = RequestMethod.GET)
    public AjaxResult selectByQuestionnaireId(@PathVariable("id") Integer questionnaireId) {
        log.info("根据问卷id查询其所有问题");
        List<Question> questionList = questionService.selectByQuestionnaireId(questionnaireId);
        return AjaxResult.success(questionList);
    }


    @RequestMapping(value = "/question/deleteByQuestionId/{id}", method = RequestMethod.DELETE)
    public AjaxResult deleteByQuestionId(@PathVariable("id") Integer questionId) {
        log.info("根据问题id删除对应一个问题");
        questionService.deleteByQuestionId(questionId);
        return AjaxResult.success();
    }






    //提交问卷部分
    @RequestMapping(value = "/useranswer/submit",method = RequestMethod.POST)
    public AjaxResult addQuestionnaireAnswer(@RequestHeader("Authorization") String token ,
                                         @RequestBody QuestionnaireAnswer questionnaireAnswer) {
        log.info("token: {}",token);
        String userid = userService.getUseridFromToken(token);
        Integer naireId = questionnaireAnswer.getQuestionnaireId();
        log.info("问卷回答 userid:{}",userid);
        log.info("新增了一份问卷回答：questionnaireAnswer : {}", questionnaireAnswer);

        QuestionnaireAnswer testAnswer = questionnaireAnswerService.getAnswerByUseridAndNaireId(userid,naireId);
        if(testAnswer != null) {
            return AjaxResult.error("您已填写过该问卷");
        }


        questionnaireAnswer.setUserid(userid);
        Integer id =  questionnaireAnswerService.addAnswerAndCount(questionnaireAnswer,userid);
        log.info(id.toString());
        return AjaxResult.success();
    }

    //查询每个用户的答卷
    @RequestMapping(value = "/useranswer/getmyanswer", method = RequestMethod.GET)
    public AjaxResult findQuestionnaireAnswer(@RequestHeader("Authorization") String token, @RequestParam Integer questionnaireId) {
        String userid = userService.getUseridFromToken(token);
        QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerService.getAnswerByUseridAndNaireId(userid,questionnaireId);
        //如果根据问卷id和userid查询不到填写情况
        if(questionnaireAnswer == null){
            return AjaxResult.error("您还没有填写该问卷");
        }
        log.info(questionnaireAnswer.toString());
        QuestionnaireAwswerVO questionnaireAwswerVO = QuestionnaireAwswerVO.builder()
                .answer(questionnaireAnswer.getAnswer())
                .build();
        return AjaxResult.success(questionnaireAwswerVO);
    }


    @RequestMapping(value = "/useranswer/anssum",method = RequestMethod.GET)
    public AjaxResult AnsSum(@RequestHeader("Authorization") String token, @RequestParam Integer questionnaireId) {
        AnswerCountDTO answerCountList = questionnaireAnswerService.answerSummery(questionnaireId);
        return AjaxResult.success(answerCountList);
    }
}
