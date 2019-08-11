package com.springboot.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        //自定义随机端口,防止冲突
//        new StartConfig(args);
        SpringApplication.run(RestServiceApplication.class, args);
    }
}
