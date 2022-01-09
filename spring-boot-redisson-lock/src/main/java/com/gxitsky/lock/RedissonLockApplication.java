package com.gxitsky.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedissonLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissonLockApplication.class, args);
    }

}
