package com.william.collegeapartmentsbacke.common.properties;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/9/14 21:17
 * @Version: 1.0
 */

@Component
@Data
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {
    private String host;
    private String username;
}
