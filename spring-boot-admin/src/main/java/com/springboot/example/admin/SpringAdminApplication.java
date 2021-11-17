package com.springboot.example.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * war包部署:继承SpringBootServletInitializer,重写configure方法
 */

@SpringBootApplication
@EnableAdminServer
public class SpringAdminApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringAdminApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAdminApplication.class, args);
    }
}
