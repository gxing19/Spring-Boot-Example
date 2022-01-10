package com.springboot.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MultipleDataSourceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDataSourceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //关闭默认的Whitelabel Error Page
        setRegisterErrorPageFilter(false);
        return application.sources(MultipleDataSourceApplication.class);
    }
}
