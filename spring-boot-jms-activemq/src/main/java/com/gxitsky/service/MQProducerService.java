package com.gxitsky.service;

import org.springframework.stereotype.Service;

import javax.jms.JMSException;

/**
 * @name: MQSendService
 * @desc: MQ发布消息
 * @author: gxing
 * @date: 2018-10-18 14:06
 **/
public interface MQProducerService {

    /**
     * activeMQ 发布消息
     *
     * @param msg
     */
    void activeMQSend(String msg) throws InterruptedException, JMSException;

}
