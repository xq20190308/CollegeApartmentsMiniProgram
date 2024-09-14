package com.william.collegeapartmentsbacke.pojo.entity.questionnaire;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("coap.questionnaire")
public class Questionnaire {

    private Integer id;
    private boolean anonymous;
    private String type;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private String isActive;
}