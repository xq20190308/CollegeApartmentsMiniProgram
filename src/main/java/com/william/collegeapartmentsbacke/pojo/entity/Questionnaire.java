package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("coap.questionnaire")
public class Questionnaire {

    private Integer id;
    private Integer type;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private String questionList;

}