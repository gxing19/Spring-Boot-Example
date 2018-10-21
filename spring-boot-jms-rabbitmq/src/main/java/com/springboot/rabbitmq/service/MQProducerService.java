package com.springboot.rabbitmq.service;

/**
 * @name: MQSendService
 * @desc: MQ发布消息
 * @author: gxing
 * @date: 2018-10-18 14:06
 **/
public interface MQProducerService {

    /**
     * rabbitMQ 发布消息
     * @param msg
     */
    void rabbitMQSend(String msg);

    /**
     * Kafka 发布消息
     * @param msg
     */
    void kafkaSend(String msg);

}
