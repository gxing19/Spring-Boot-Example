package com.springboot.rabbitmq.service.impl;

import com.springboot.rabbitmq.entity.User;
import com.springboot.rabbitmq.service.TopicProducerService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @name: TopicProducerServiceImpl
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-26 17:33
 **/
@Service
public class TopicProducerServiceImpl implements TopicProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    @Override
    public void rabbitMQSendStr(String msg) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), "topic.news", "Top Ten News");
    }

    @Override
    public void rabbitMQSendObj(User andy) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), "topic.news.nba", "2018-2019 NBA First Battle Start");
    }
}
