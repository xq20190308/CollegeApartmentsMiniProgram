package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("coap.question")
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private String id;
    private Integer type;
    private String name;
    private String describe;
    private String content;
    private String questionnaire;

}
