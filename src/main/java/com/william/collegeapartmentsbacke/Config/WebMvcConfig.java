package com.william.collegeapartmentsbacke.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${localFileUrl}")
    private String localFileUrl;

    @Value("${mapFileUrl}")
    private String mapFileUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
     //windows本地文件目录
       registry.addResourceHandler(mapFileUrl+"**").addResourceLocations("file:"+localFileUrl);
    }
}

