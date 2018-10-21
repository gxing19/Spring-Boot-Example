package com.springboot.rabbitmq.controller;

import com.springboot.rabbitmq.service.MQProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: MQSendController
 * @desc: MQ 生产者
 * @author: gxing
 * @date: 2018-10-18 14:02
 **/
@RestController
@RequestMapping("/producer")
public class MQProducerController {
    private static final Logger logger = LogManager.getLogger(MQProducerController.class);

    @Autowired
    private MQProducerService mqProducerService;

    /**
     * RabbitMQ 发布消息
     *
     * @param msg
     * @return
     */
    @RequestMapping("/rabbitmq/send")
    public String rabbitMQSendMsg(String msg) {
        mqProducerService.rabbitMQSend(msg);
        return null;
    }

    /**
     * Kafka 发布消息
     *
     * @param msg
     * @return
     */
    @RequestMapping("/Kafka/send")
    public String kafkaSendMsg(String msg) {
        mqProducerService.kafkaSend(msg);
        return null;
    }

}