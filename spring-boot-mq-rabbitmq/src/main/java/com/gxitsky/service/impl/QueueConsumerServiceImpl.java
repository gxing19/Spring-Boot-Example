package com.gxitsky.service.impl;

import com.gxitsky.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @name: MQConsumerServiceImpl
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-18 14:54
 **/
@Service
public class QueueConsumerServiceImpl {

    /**
     * 多个消费者订阅 queue 类型同一个渠道的消息，
     * 接收消息的顺序与发送消息的顺序并不相同，
     * 多个消费者平均消费消息。
     */

    /**
     * 监听my-queue 文本消息
     */
    @RabbitListener(queues = "my-queue")
    public void rabbitMQReceive1(String msg) {
//    public void rabbitMQReceive1(String msg) {
        System.out.println("client1 receive my-queue msg:" + msg);
    }

    @RabbitListener(queues = "my-queue")
    public void rabbitMQReceive2(String msg) {
        System.out.println("client2 receive my-queue msg:" + msg);
    }

    @RabbitListener(queues = "my-queue")
    public void rabbitMQReceive3(String msg) {
        System.out.println("client3 receive my-queue msg:" + msg);
    }

    /**
     * 监听user 对象消息消息
     */
    @RabbitListener(queues = "object")
    public void rabbitMQReceiveUser(User user) {
        System.out.println("receive user msg:" + user);
    }
}