package com.springboot.example.aop.common.configuration;

import com.springboot.example.aop.common.component.NoRepeatInterceptor;
import com.springboot.example.aop.common.component.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
//        registry.addInterceptor(noRepeatInterceptor())
//                .addPathPatterns("/**");
//        registry.addInterceptor(new APIHandleTimeInterceptor())
//                .addPathPatterns("/**");
//        registry.addInterceptor(new RequestLimitInterceptor());
    }

    @Bean
    public NoRepeatInterceptor noRepeatInterceptor(){
        return new NoRepeatInterceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

}
