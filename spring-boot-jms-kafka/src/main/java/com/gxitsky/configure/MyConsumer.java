package com.gxitsky.configure;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @name: KafkaConsumer
 * @desc: TODO
 * @author: gxing
 * @date: 2019-05-09 15:13
 **/
@Component
public class MyConsumer {

    @KafkaListener(topics = "NBA", groupId = "${spring.kafka.consumer.group-id}")
    public void processMessage(String content) {
        System.out.println(content);
    }
}
