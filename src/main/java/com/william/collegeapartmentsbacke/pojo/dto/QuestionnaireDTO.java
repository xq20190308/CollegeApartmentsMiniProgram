package com.william.collegeapartmentsbacke.pojo.dto;

import com.william.collegeapartmentsbacke.pojo.entity.Question;
import com.william.collegeapartmentsbacke.pojo.entity.Questionnaire;
import lombok.Data;

import java.util.List;

@Data
public class QuestionnaireDTO extends Questionnaire {
    private Integer id;
    private Integer type;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private List<Question> questionList;

}
