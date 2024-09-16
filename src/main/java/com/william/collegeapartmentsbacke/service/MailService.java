package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.ToEmail;
import jakarta.mail.MessagingException;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/16 11:00
 * @Version: 1.0
 */
public interface MailService {
    Result commonEmail(ToEmail toEmail);
    Result htmlEmail(ToEmail toEmail) throws MessagingException;
    Result sendMimeMail(ToEmail toEmail);
}
