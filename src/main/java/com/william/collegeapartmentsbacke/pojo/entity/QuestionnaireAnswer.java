package com.william.collegeapartmentsbacke.pojo.entity;


import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireAnswer {
    private Integer id;
    private String answer;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitTime;
    private Integer questionnaireId;
    private String userid;
}
