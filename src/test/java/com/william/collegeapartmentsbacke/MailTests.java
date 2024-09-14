package com.william.collegeapartmentsbacke;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.ToEmail;
import com.william.collegeapartmentsbacke.service.MailService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/14 22:08
 * @Version: 1.0
 */

@SpringBootTest
public class MailTests {
    private static final Logger log = LoggerFactory.getLogger(MailTests.class);

    @Autowired
    private MailService mailService;

    @Test
    public void contextLoads() {
        ToEmail toEmail = new ToEmail(new String[]{"2516743200@qq.com"}, "测试邮件", "测试邮件内容");
        Result result = mailService.commonEmail(toEmail);
        log.info(result.toString());
    }

}
