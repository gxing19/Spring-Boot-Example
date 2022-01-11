package com.gxitsky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulServiceApplication {

    public static void main(String[] args) {
        //自定义随机端口,防止冲突
//        new StartConfig(args);
        SpringApplication.run(RestfulServiceApplication.class, args);
    }
}
