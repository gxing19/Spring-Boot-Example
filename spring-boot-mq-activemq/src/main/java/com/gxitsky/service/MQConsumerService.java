package com.gxitsky.service;

import org.springframework.stereotype.Service;

/**
 * @name: MQConsumerService
 * @desc: MQ 消费者
 * @author: gxing
 * @date: 2018-10-18 14:20
 **/
public interface MQConsumerService {
    void activeMQQueueReceive(String message);

    void activeMQTopicReceive(String message);
}
