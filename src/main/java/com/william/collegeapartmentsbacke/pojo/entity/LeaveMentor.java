package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

//向任课老师请假
@Data
@TableName("coap.leave_mentor")
public class LeaveMentor {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userId;
    private String postId;
    private String courseName;
    private String courseTime;
    private String mentorId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
