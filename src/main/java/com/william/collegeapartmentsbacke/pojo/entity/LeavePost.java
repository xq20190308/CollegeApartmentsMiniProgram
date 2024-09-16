package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
//    @TableField(fill = FieldFill.UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
