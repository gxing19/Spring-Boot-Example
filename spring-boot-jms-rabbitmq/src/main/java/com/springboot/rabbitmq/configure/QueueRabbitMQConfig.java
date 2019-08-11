package com.springboot.rabbitmq.configure;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @name: MQComponent
 * @desc: 消息组件
 * @author: gxing
 * @date: 2018-10-18 14:47
 **/
@Configuration
public class QueueRabbitMQConfig {

    private static final String QUEUE_NAME_1 = "my-queue";
    private static final String QUEUE_NAME_2 = "object";

    @Bean
    public Queue strQueue() {
        return new Queue(QUEUE_NAME_1);
    }

    @Bean
    public Queue objQueue() {
        return new Queue(QUEUE_NAME_2);
    }

}