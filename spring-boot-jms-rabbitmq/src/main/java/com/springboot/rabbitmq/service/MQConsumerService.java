package com.springboot.rabbitmq.service;

/**
 * @name: MQConsumerService
 * @desc: MQ 消费者
 * @author: gxing
 * @date: 2018-10-18 14:20
 **/
public interface MQConsumerService {
    void rabbitMQReceive(String msg);

    void kafkaReceive(String msg);
}
