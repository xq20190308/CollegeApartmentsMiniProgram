package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/14 15:25
 * @Version: 1.0
 */
@RestController
@RequestMapping("/testemail")
public class EmailTestController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String senderEmail;

    @NoNeedLogin
    @RequestMapping("/send")
    public void sendEmail(String to, String subject, String content) throws MessagingException, UnsupportedEncodingException {
        // 创建一个邮件消息
        MimeMessage message = javaMailSender.createMimeMessage();

        // 创建 MimeMessageHelper
        MimeMessageHelper helper = new MimeMessageHelper(message, false);

        // 发件人邮箱和名称
        helper.setFrom(senderEmail, "william");
        // 收件人邮箱
        helper.setTo("1844118046@qq.com");
        // 邮件标题
        helper.setSubject("Hello,EmailTest");
        // 邮件正文，第二个参数表示是否是HTML正文
        helper.setText("Hello <strong> World!!!!!!!!!!!!!!!!!!!!!!!!!!</strong>！", true);

        // 发送
        javaMailSender.send(message);
    }


}
