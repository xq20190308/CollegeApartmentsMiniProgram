package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.ToEmail;
import com.william.collegeapartmentsbacke.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/14 15:25
 * @Version: 1.0
 */
@RestController
@RequestMapping("/testemail")
public class EmailTestController {

    Logger logger = LoggerFactory.getLogger(EmailTestController.class);
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailService mailService;

    @Value("${spring.mail.username}")
    String senderEmail;

    @NoNeedLogin
    @RequestMapping("/send")
    public Result sendEmail(String[] tos, String subject, String content, MultipartFile[] files) {
        // 日志信息过滤敏感数据
        logger.info("发送邮件：to {}, subject {}, files", Arrays.toString(tos), subject);

        // 输入参数验证
        if (tos == null || tos.length == 0 || subject == null || subject.isEmpty() || content == null || content.isEmpty()) {
            return Result.error("参数错误");
        }

        try {
            // 发送邮件
//            mailService.commonEmail(new ToEmail(tos, subject, content, files));
            mailService.sendMimeMail(new ToEmail(tos, subject, content, files));
        } catch (MailException e) {
            logger.error("发送邮件失败", e);
            return Result.error("发送失败：" + e.getMessage());
        }

        return Result.success("发送成功");
    }
    @NoNeedLogin
    @RequestMapping("/sendText")
    public Result sendTextEmail(String[] tos, String subject, String content) {
        // 日志信息过滤敏感数据
        logger.info("发送邮件：to {}, subject {}, files", Arrays.toString(tos), subject);

        // 输入参数验证
        if (tos == null || tos.length == 0 || subject == null || subject.isEmpty() || content == null || content.isEmpty()) {
            return Result.error("参数错误");
        }

        try {
            // 发送邮件
            mailService.commonEmail(new ToEmail(tos, subject, content,null));
//            mailService.sendMimeMail(new ToEmail(tos, subject, content, files));
        } catch (MailException e) {
            logger.error("发送邮件失败", e);
            return Result.error("发送失败：" + e.getMessage());
        }

        return Result.success("发送成功");
    }


//    @NoNeedLogin
//    @RequestMapping("/sendstatic")
//    public void sendStaticEmail(String to, String subject, String content) throws MessagingException, UnsupportedEncodingException {
//        ToEmail toEmail = new ToEmail(new String[]{to}, subject, content);
//        mailService.staticEmail(toEmail, null, "static");
//    }


}
