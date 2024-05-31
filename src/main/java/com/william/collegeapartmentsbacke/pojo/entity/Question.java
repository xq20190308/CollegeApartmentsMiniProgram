package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("coap.question")
public class Question {
    private Integer id;
    private Integer type;
    private String name;
    private String description;
    private String content;
    private Integer questionnaireId;

}
