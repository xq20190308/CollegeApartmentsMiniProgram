package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.ToEmail;
import com.william.collegeapartmentsbacke.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    private MailService mailService;

    @Value("${spring.mail.username}")
    String senderEmail;

    @NoNeedLogin
    @RequestMapping("/send")
    public AjaxResult sendEmail(String[] tos, String subject, String content, MultipartFile[] files) {
        // 日志信息过滤敏感数据
        logger.info("发送邮件：to {}, subject {}, files", Arrays.toString(tos), subject);

        // 输入参数验证
        if (tos == null || tos.length == 0 || subject == null || subject.isEmpty() || content == null || content.isEmpty()) {
            return AjaxResult.error("参数错误");
        }

        try {
            // 发送邮件
//            mailService.commonEmail(new ToEmail(tos, subject, content, files));
            mailService.sendMimeMail(new ToEmail(tos, subject, content, files));
        } catch (MailException e) {
            logger.error("发送邮件失败", e);
            return AjaxResult.error("发送失败：" + e.getMessage());
        }

        return AjaxResult.success("发送成功");
    }
    @NoNeedLogin
    @RequestMapping("/sendText")
    public AjaxResult sendTextEmail(String[] tos, String subject, String content) {
        // 日志信息过滤敏感数据
        logger.info("发送邮件：to {}, subject {}, files", Arrays.toString(tos), subject);

        // 输入参数验证
        if (tos == null || tos.length == 0 || subject == null || subject.isEmpty() || content == null || content.isEmpty()) {
            return AjaxResult.error("参数错误");
        }

        try {
            // 发送邮件
            mailService.commonEmail(new ToEmail(tos, subject, content,null));
//            mailService.sendMimeMail(new ToEmail(tos, subject, content, files));
        } catch (MailException e) {
            logger.error("发送邮件失败", e);
            return AjaxResult.error("发送失败：" + e.getMessage());
        }

        return AjaxResult.success("发送成功");
    }


//    @NoNeedLogin
//    @RequestMapping("/sendstatic")
//    public void sendStaticEmail(String to, String subject, String content) throws MessagingException, UnsupportedEncodingException {
//        ToEmail toEmail = new ToEmail(new String[]{to}, subject, content);
//        mailService.staticEmail(toEmail, null, "static");
//    }


}
