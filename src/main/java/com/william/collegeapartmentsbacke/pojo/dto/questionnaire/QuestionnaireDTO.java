package com.william.collegeapartmentsbacke.pojo.dto.questionnaire;

import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.Question;
import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.Questionnaire;
import lombok.Data;

import java.util.List;

@Data
public class QuestionnaireDTO extends Questionnaire {
    private Integer id;
    private Boolean anonymous;
    private String type;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private String isActive;
    private List<Question> questionList;

}
