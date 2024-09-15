package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/14 21:45
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToEmail implements Serializable {

    /**
     * 邮件接收方，可多人
     */
    private String[] tos;

//    private String cc;//抄送（多个邮箱则用逗号","隔开）
//    private String bcc;//密送（多个邮箱则用逗号","隔开）
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

    private MultipartFile[] multipartFiles;//邮件附件

    public ToEmail(String mail, String html邮件, String content) {
    }
}
