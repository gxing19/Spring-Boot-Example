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
public class RabbitMQConfig {

    @Bean
    public Queue declareQueue(){
        return new Queue("my-queue");
    }



}