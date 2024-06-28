package com.william.collegeapartmentsbacke.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/23 14:59
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
public class MessageDTO {
    private Integer type;
    private String data;
    private String senderUserId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;
}
