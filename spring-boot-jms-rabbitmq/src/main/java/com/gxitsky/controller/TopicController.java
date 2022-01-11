package com.gxitsky.controller;

import com.gxitsky.entity.User;
import com.gxitsky.service.TopicProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: TopicController
 * @desc: Topic
 * @author: gxing
 * @date: 2018-10-26 17:29
 **/
@RestController
@RequestMapping("/topic")
public class TopicController {

    private static final Logger logger = LogManager.getLogger(QueueController.class);

    @Autowired
    private TopicProducerService topicProducerService;

    /**
     * RabbitMQ 发布文本消息
     *
     * @param msg
     * @return
     */
    @RequestMapping("/news")
    public String rabbitMQSendStr(String msg) {
        topicProducerService.rabbitMQSendStr(msg);
        return null;
    }

    /**
     * RabbitMQ 发布对象消息
     * 前提是对象要实现序列化接口
     *
     * @return
     */
    @RequestMapping("/news/nba")
    public String rabbitMQSendObj() {
        topicProducerService.rabbitMQSendObj(new User("Andy", 33));
        return null;
    }
}