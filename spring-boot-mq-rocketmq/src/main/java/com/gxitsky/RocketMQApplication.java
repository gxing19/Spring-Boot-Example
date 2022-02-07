package com.gxitsky;

import org.apache.rocketmq.common.message.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQApplication.class, args);
        Message message = new Message();
    }

}
