package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("coap.questionnaire")
public class Questionnaire {

    private String id;
    private Integer type;
    private String name;
    private String descr;
    private String startTime;
    private String endTime;
    private String questionList;

}