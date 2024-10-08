package com.william.collegeapartmentsbacke.pojo.dto.questionnaire;

import com.william.collegeapartmentsbacke.pojo.entity.questionnaire.AnswerCount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AnswerCountDTO {
    private List<AnswerCount> answerCountList;
    private Integer numOfAnswers;
}
