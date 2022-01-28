package com.gxitsky.controller;

import com.gxitsky.entity.User;
import com.gxitsky.service.QueueProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: MQSendController
 * @desc: Queue
 * @author: gxing
 * @date: 2018-10-18 14:02
 **/
@RestController
@RequestMapping("/queue")
public class QueueController {
    private static final Logger logger = LogManager.getLogger(QueueController.class);

    @Autowired
    private QueueProducerService queueProducerService;

    /**
     * RabbitMQ 发布文本消息
     *
     * @param msg
     * @return
     */
    @RequestMapping("/sendStr")
    public String rabbitMQSendStr(String msg) throws InterruptedException {
        queueProducerService.rabbitMQSendStr(msg);
        return null;
    }

    /**
     * RabbitMQ 发布对象消息
     * 前提是对象要实现序列化接口
     *
     * @return
     */
    @RequestMapping("/sendObj")
    public String rabbitMQSendObj() {
        queueProducerService.rabbitMQSendObj(new User("Andy", 33));
        return null;
    }
}