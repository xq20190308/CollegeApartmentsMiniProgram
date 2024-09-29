package com.william.collegeapartmentsbacke.pojo.entity.questionnaire;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/4 22:12
 * @Version: 1.0
 */
@Data
@TableName("coap.simple_answer")
public class SimpleAnswer implements Serializable {
    private Integer id;
    private Integer questionId;
    private String answer;
    private Integer naireId;
    private String userid;
}
