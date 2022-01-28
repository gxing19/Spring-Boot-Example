package com.gxitsky.service.impl;

import com.gxitsky.service.MQConsumerService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @name: MQConsumerServiceImpl
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-18 14:54
 **/
@Service
public class MQConsumerServiceImpl implements MQConsumerService {

    /**
     * 监听queue消息
     *
     * @param message
     */
    @Override
    @JmsListener(destination = "my-queue")
    public void activeMQQueueReceive(String message) {
        System.out.println("监听收到my-queue消息:" + message);
    }

    /**
     * 监听topic消息
     *
     * @param message
     */
    @Override
    @JmsListener(destination = "my-topic", containerFactory = "topicListenerContainer")
    public void activeMQTopicReceive(String message) {
        System.out.println("监听收到my-topic消息:" + message);
    }

}