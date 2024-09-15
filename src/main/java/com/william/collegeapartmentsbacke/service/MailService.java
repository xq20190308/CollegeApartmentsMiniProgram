package com.william.collegeapartmentsbacke.service;


import com.william.collegeapartmentsbacke.common.properties.MailProperties;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.ToEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/14 21:04
 * @Version: 1.0
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailProperties mailProperties;

    public Result commonEmail(ToEmail toEmail) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(mailProperties.getUsername());
        //谁要接收
        message.setTo(toEmail.getTos());
        //邮件标题
        message.setSubject(toEmail.getSubject());
        //邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
            return Result.success("给" + toEmail.getTos() + "普通邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            return Result.error("普通邮件发送失败");
        }


    }
}