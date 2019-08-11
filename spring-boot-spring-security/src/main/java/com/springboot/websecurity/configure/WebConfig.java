package com.springboot.websecurity.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/404").setViewName("404");
        //根目录默认定位到首页
        registry.addRedirectViewController("/", "/index");
        registry.addRedirectViewController("/selectAll", "/city/queryAll");
    }
}