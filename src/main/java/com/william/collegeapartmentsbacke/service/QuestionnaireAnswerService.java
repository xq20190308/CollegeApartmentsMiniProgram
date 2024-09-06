package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.dto.AnswerCountDTO;
import com.william.collegeapartmentsbacke.pojo.entity.AnswerCount;
import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;

import java.util.List;

public interface QuestionnaireAnswerService {
//    Integer addQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer);

    Integer addAnswerAndCount(QuestionnaireAnswer questionnaireAnswer, String userid);

    QuestionnaireAnswer getAnswerByUseridAndNaireId(String userid, Integer naireid);

    AnswerCountDTO answerSummery(Integer naireid);

    void deleteAnswer(Integer naireid);
}
