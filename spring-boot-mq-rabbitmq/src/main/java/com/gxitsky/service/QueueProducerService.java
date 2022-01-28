package com.gxitsky.service;

import com.gxitsky.entity.User;

/**
 * @name: MQSendService
 * @desc: MQ发布消息
 * @author: gxing
 * @date: 2018-10-18 14:06
 **/
public interface QueueProducerService {

    /**
     * 发布文本消息
     *
     * @param msg
     */
    void rabbitMQSendStr(String msg) throws InterruptedException;

    /**
     * 发布对象消息
     *
     * @param user
     */
    void rabbitMQSendObj(User user);

}
