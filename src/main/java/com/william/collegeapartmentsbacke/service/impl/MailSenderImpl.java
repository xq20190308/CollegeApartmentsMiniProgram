package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.common.properties.MailProperties;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: William
 * @Description: 实现Transport对象池化的邮件发送服务
 * @Date: 2024/9/16 17:12
 * @Version: 1.0
 */
public class MailSenderImpl extends JavaMailSenderImpl {

    @Autowired
    MailProperties mailProperties;

    private static final String HEADER_MESSAGE_ID = "Message-ID";
    private GenericObjectPool<Transport> transportPool;

    public MailSenderImpl() {
        // 初始化Transport对象池
        GenericObjectPoolConfig<Transport> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(10); // 最大连接数
        config.setMaxIdle(5);   // 最大空闲连接数
        config.setMinIdle(1);   // 最小空闲连接数
        config.setMaxWaitMillis(5000); // 最大等待时间

        PooledObjectFactory<Transport> factory = new BasePooledObjectFactory<>() {
            @Override
            public Transport create() throws Exception {
                Properties props = new Properties();
                props.put("mail.smtp.host", mailProperties.getHost());
                props.put("mail.smtp.port", mailProperties.getPort());
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                Session session = Session.getInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
                    }
                });
                return session.getTransport("smtp");
            }

            @Override
            public PooledObject<Transport> wrap(Transport transport) {
                return new DefaultPooledObject<>(transport);
            }
        };

        transportPool = new GenericObjectPool<>(factory, config);
    }

    @Override
    protected synchronized void doSend(MimeMessage[] mimeMessages, Object[] originalMessages) {
        Map<Object, Exception> failedMessages = new LinkedHashMap<>();

        for (int i = 0; i < mimeMessages.length; i++) {
            try {
                Transport transport = transportPool.borrowObject();

                // Send message via current transport...
                MimeMessage mimeMessage = mimeMessages[i];
                try {
                    if (mimeMessage.getSentDate() == null) {
                        mimeMessage.setSentDate(new Date());
                    }
                    String messageId = mimeMessage.getMessageID();

                    mimeMessage.saveChanges();

                    if (messageId != null) {
                        // Preserve explicitly specified message id...
                        mimeMessage.setHeader(HEADER_MESSAGE_ID, messageId);
                    }
                    Address[] addresses = mimeMessage.getAllRecipients();

                    // 确保连接成功
                    if (!transport.isConnected()) {
                        transport.connect();
                    }

                    // 发送邮件
                    transport.sendMessage(mimeMessage, (addresses != null ? addresses : new Address[0]));
                } finally {
                    // 关闭连接
                    if (transport.isConnected()) {
                        transport.close();
                    }
                    transportPool.returnObject(transport);
                }
            } catch (Exception ex) {
                Object original = (originalMessages != null ? originalMessages[i] : mimeMessages[i]);
                failedMessages.put(original, ex);
            }
        }

        if (!failedMessages.isEmpty()) {
            throw new MailSendException(failedMessages);
        }
    }

}
