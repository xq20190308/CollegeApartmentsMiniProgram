package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/8/21 21:35
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerStatistics {
    private Integer id;
    private Integer naireId;
    private Integer questionId;
    private Integer answerType;
    private String choiceCount;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;
}
