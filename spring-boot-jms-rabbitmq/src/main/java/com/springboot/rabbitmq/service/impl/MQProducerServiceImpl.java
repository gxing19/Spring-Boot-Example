package com.springboot.rabbitmq.service.impl;

import com.springboot.rabbitmq.service.MQProducerService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @name: MQSendServiceImpl
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-18 14:07
 **/
@Service
public class MQProducerServiceImpl implements MQProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void rabbitMQSend(String msg) {
        rabbitTemplate.convertAndSend("my-queue", msg);
    }

    @Override
    public void kafkaSend(String msg) {

    }
}