package com.gxitsky.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @name: TopicConsumerServiceImpl
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-26 17:33
 **/
@Service
public class TopicConsumerServiceImpl {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "topic.news")
    public void rabbitMQReceive1(String msg) {
        System.out.println("client1 receive topic.news     msg: " + msg);
    }

    @RabbitListener(queues = "topic.news.nba")
    public void rabbitMQReceive2(String msg) {
        System.out.println("client2 receive topic.news.nba msg: " + msg);
    }

}