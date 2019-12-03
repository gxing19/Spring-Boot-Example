package com.springboot.aop.common.configuration;

import com.springboot.aop.common.component.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
//        registry.addInterceptor(new APIHandleTimeInterceptor())
//                .addPathPatterns("/**");
//        registry.addInterceptor(new RequestLimitInterceptor());
    }
}
