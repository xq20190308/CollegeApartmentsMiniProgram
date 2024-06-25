package com.william.collegeapartmentsbacke.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/20 16:26
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientMessageVO implements Serializable {
    private Integer type;
    private String senderUserId;
    private String data;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;


}
