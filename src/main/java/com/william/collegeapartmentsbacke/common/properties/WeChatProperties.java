package com.william.collegeapartmentsbacke.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "zhrc.wechat")
@Data
public class WeChatProperties {
    private String appid;
    private String secret;
}
