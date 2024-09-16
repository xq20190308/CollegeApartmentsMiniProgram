package com.william.collegeapartmentsbacke.config;

import com.william.collegeapartmentsbacke.service.impl.MailSenderImpl;
import com.william.collegeapartmentsbacke.service.impl.MailServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.util.Map;
import java.util.Properties;


/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/16 16:48
 * @Version: 1.0
 */

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailConfig {


    @Bean
    @ConditionalOnMissingBean(JavaMailSender.class)
    MailSenderImpl mailSender(MailProperties properties) {
        MailSenderImpl sender = new MailSenderImpl();
        applyProperties(properties, sender);
        return sender;
    }

    private void applyProperties(MailProperties properties, JavaMailSenderImpl sender) {
        sender.setHost(properties.getHost());
        if (properties.getPort() != null) {
            sender.setPort(properties.getPort());
        }
        sender.setUsername(properties.getUsername());
        sender.setPassword(properties.getPassword());
        sender.setProtocol(properties.getProtocol());
        if (properties.getDefaultEncoding() != null) {
            sender.setDefaultEncoding(properties.getDefaultEncoding().name());
        }
        if (!properties.getProperties().isEmpty()) {
            sender.setJavaMailProperties(asProperties(properties.getProperties()));
        }
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }
}

