package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.dto.QuestionnaireDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import com.william.collegeapartmentsbacke.service.QuestionService;
import com.william.collegeapartmentsbacke.service.QuestionnaireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

        Integer questionnaireId = questionnaireService.simpleAdd();

        log.info(questionnaireDto.toString());
        List<Question> questionList = questionnaireDto.getQuestionList();

        List<Integer> questionIdList = questionService.addQuestions(questionList,questionnaireId);

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(questionnaireId);
        questionnaire.setType(questionnaireDto.getType());
        questionnaire.setName(questionnaireDto.getName());
        questionnaire.setDescription(questionnaireDto.getDescription());
        questionnaire.setStartTime(questionnaireDto.getStartTime());
        questionnaire.setEndTime(questionnaireDto.getEndTime());

        String qidlistStr = questionIdList.toString();
        qidlistStr = qidlistStr.replaceAll("(\\d+)", "\"$1\""); // 为每个数字添加引号
        questionnaire.setQuestionIdList(qidlistStr);


        questionnaireService.totallyadd(questionnaire);
        log.info(questionnaire.toString());
        return Result.success();
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("id") Integer id) {
        questionnaireService.deleteById(id);
/*        questionService.deletByQuestionnaire(id);*/
        return Result.success();
    }

}
