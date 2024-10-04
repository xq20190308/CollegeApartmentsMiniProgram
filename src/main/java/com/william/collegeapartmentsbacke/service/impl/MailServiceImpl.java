package com.william.collegeapartmentsbacke.service.impl;
import com.william.collegeapartmentsbacke.common.properties.MailProperties;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.ToEmail;
import com.william.collegeapartmentsbacke.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/14 21:04
 * @Version: 1.0
 */

@Service
public class MailServiceImpl implements MailService  {
    Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private MailSenderImpl mailSender;
    @Autowired
    private MailProperties mailProperties;

    @Override
    public AjaxResult commonEmail(ToEmail toEmail) {
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
            return AjaxResult.success("给" + toEmail.getTos() + "普通邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            return AjaxResult.error("普通邮件发送失败");
        }
    }

    @Override
    public AjaxResult htmlEmail(ToEmail toEmail) throws MessagingException {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
        //谁发
        minehelper.setFrom(mailProperties.getUsername());
        //谁要接收
        minehelper.setTo(toEmail.getTos());
        //邮件主题
        minehelper.setSubject(toEmail.getSubject());
        //邮件内容   true 表示带有附件或html
        minehelper.setText(toEmail.getContent(), true);
        try {
            mailSender.send(message);
            return AjaxResult.success("给" + toEmail.getTos() + "html邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            return AjaxResult.error("html邮件发送失败");
        }
    }

    @Override
    //构建复杂邮件信息类(带附件)
    public AjaxResult sendMimeMail(ToEmail toEmail) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);//true表示支持复杂类型

            messageHelper.setFrom(mailProperties.getUsername());//邮件发信人
            messageHelper.setTo(toEmail.getTos());//邮件收信人
            messageHelper.setSubject(toEmail.getSubject());//邮件主题
            messageHelper.setText(toEmail.getContent());//邮件内容
            //密送和抄送，暂时不用
//            if (!StringUtils.isEmpty(mailVo.getCc())) {//抄送
//                messageHelper.setCc(mailVo.getCc().split(","));
//            }
//            if (!StringUtils.isEmpty(mailVo.getBcc())) {//密送
//                messageHelper.setCc(mailVo.getBcc().split(","));
//            }
            if (toEmail.getMultipartFiles() != null) {//添加邮件附件
                for (MultipartFile multipartFile : toEmail.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
            logger.info("发送邮件成功：{}->{}", mailProperties.getUsername(), toEmail.getTos());
            return AjaxResult.success("附件发送成功");
        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }


}