package com.william.collegeapartmentsbacke.pojo.dto;

import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import lombok.Data;

@Data
public class QuestionnaireDTO extends Questionnaire {

    private String question;

}
