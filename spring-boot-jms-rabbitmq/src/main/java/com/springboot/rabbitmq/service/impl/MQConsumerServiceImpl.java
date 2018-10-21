package com.springboot.rabbitmq.service.impl;

import com.springboot.rabbitmq.service.MQConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @name: MQConsumerServiceImpl
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-18 14:54
 **/
@Service
public class MQConsumerServiceImpl implements MQConsumerService{

    /**
     * 监听queue消息
     */
    @Override
    @RabbitListener(queues = "my-queue")
    public void rabbitMQReceive(String msg) {
        System.out.println("监听收到my-queue消息:" + msg);
    }



    /**
     * 监听topic消息
     */
    /*@Override
    @JmsListener(destination = "my-topic", containerFactory = "topicListenerContainer")
    public void activeMQTopicReceive(String message) {
        System.out.println("监听收到my-topic消息:" + message);
    }*/

    @Override
    public void kafkaReceive(String msg) {

    }


}