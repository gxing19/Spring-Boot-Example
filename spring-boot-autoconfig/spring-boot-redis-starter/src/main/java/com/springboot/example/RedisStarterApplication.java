package com.springboot.example;

import com.springboot.example.autoconfig.RedisConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RedisStarterApplication {

    @Autowired
    RedisConfiguration redisConfiguration;

    @RequestMapping("/")
    public String index() {
        return redisConfiguration.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisStarterApplication.class, args);
    }
}
