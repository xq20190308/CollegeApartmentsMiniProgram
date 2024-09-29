package com.william.collegeapartmentsbacke.pojo.vo.questionnaire;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class QuestionnaireAwswerVO implements Serializable {
    private String answer;
}
