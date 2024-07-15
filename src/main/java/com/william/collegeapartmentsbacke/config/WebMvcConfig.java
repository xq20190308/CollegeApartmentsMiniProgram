package com.william.collegeapartmentsbacke.config;

import com.william.collegeapartmentsbacke.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${localFileUrl}")
    private String localFileUrl;

    @Value("${mapFileUrl}")
    private String mapFileUrl;

    @Autowired
    private JwtInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //windows本地文件目录
       registry.addResourceHandler(mapFileUrl+"**").addResourceLocations("file:"+localFileUrl);

    }


    /**添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/error", "/error/**");
    }

}
