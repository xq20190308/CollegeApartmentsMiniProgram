package com.william.collegeapartmentsbacke.pojo.entity;


import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireAnswer {
    private Integer id;
    private String answer;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private DateTime submitTime;
    private int questionnaireId;
    private String userid;
}
