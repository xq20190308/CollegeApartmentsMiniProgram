package com.william.collegeapartmentsbacke.service.questionnaire;

import com.william.collegeapartmentsbacke.pojo.dto.questionnaire.AnswerCountDTO;
import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.QuestionnaireAnswer;

public interface QuestionnaireAnswerService {
//    Integer addQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer);

    Integer addAnswerAndCount(QuestionnaireAnswer questionnaireAnswer, String userid);

    QuestionnaireAnswer getAnswerByUseridAndNaireId(String userid, Integer naireid);

    AnswerCountDTO answerSummery(Integer naireid);

    void deleteAnswer(Integer naireid);
}
