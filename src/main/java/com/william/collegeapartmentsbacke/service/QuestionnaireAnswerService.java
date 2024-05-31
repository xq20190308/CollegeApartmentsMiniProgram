package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.QuestionnaireAnswer;

public interface QuestionnaireAnswerService {
    Integer addQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer);

    QuestionnaireAnswer getAnswerByUseridAndNaireId(String userid, Integer naireid);
}
