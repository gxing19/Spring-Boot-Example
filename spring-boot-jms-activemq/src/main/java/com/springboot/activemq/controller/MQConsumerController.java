package com.springboot.activemq.controller;

import com.springboot.activemq.service.MQConsumerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: MQConsumerController
 * @desc: MQ 消费者
 * @author: gxing
 * @date: 2018-10-18 14:15
 **/
@RestController
@RequestMapping("/consumer")
public class MQConsumerController {

    private static final Logger logger = LogManager.getLogger(MQProducerController.class);

    @Autowired
    private MQConsumerService mqConsumerService;

    /**
     * ActiveMQ 接收
     * @param message
     * @return
     */
    @RequestMapping("/activemq/get")
    public void activeMQSendMsg(String message){
        mqConsumerService.activeMQQueueReceive(message);
    }

}