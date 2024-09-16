package com.william.collegeapartmentsbacke.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String courseSTime;
    private String courseETime;
    private String mentorId;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
