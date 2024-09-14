package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("coap.leave_post")
public class LeavePost implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userId;
    private String trueName;
    private String isOut;
    private String reason;
    private String file;
    private String startTime;
    private String endTime;
    private String reviewerId;
    private String status;
//    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
//    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;
}
