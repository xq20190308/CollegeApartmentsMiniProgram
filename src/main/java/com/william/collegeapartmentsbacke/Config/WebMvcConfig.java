package com.william.collegeapartmentsbacke.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
     //windows本地文件目录
       registry.addResourceHandler("/static/**").addResourceLocations("file:E:/static/");
    }
}

