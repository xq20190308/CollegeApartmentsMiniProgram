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
        ToEmail toEmail = new ToEmail(new String[]{"2092107214@qq.com"}, "测试邮件", "测试邮件内容");
        Result result = mailService.commonEmail(toEmail);
        log.info(result.toString());
    }
    @Test
    public void testHtml() throws Exception {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h1>这是Html格式邮件!,不信你看邮件，我字体比一般字体还要大</h1>\n" +
                "</body>\n" +
                "</html>";
        mailService.htmlEmail(new ToEmail(new String[]{"2092107214@qq.com"},"Html邮件",content));
    }


}
