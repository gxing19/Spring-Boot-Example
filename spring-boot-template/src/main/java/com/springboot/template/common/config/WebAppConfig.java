package com.springboot.template.common.config;

import com.springboot.template.common.interceptor.LogIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @name: WebConfig
 * @desc: TODO
 * @author: gxing
 * @date: 2018-11-09 17:48
 **/
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogIdInterceptor());
    }
}