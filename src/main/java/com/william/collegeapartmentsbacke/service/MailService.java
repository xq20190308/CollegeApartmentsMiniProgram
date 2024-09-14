package com.william.collegeapartmentsbacke.service;


import org.springframework.beans.factory.annotation.Autowired;
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
    private JavaMailSenderImpl mailSender;




}