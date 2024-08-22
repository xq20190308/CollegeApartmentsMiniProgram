package com.william.collegeapartmentsbacke.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class DefaultConfig {
    @Value("${defaults.password}")
    private String password;
    @Value("${defaults.userLevel}")
    private String userLevel;
    @Value("${defaults.avatarUrl}")
    private String avatarUrl;
    public String getPassword() {
        return password;
    }
    public String getUserLevel() {
        return userLevel;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
}

