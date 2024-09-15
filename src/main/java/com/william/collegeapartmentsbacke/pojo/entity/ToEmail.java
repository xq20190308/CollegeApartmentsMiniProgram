package com.william.collegeapartmentsbacke.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ToEmail implements Serializable {

    /**
     * 邮件接收方，可多人
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
}
