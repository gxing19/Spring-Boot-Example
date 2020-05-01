package com.springboot.rabbitmq.service.impl;

import com.springboot.rabbitmq.entity.User;
import com.springboot.rabbitmq.service.QueueProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @name: MQSendServiceImpl
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-18 14:07
 **/
@Service
public class QueueProducerServiceImpl implements QueueProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 发送普通消息到队列-queue
     *
     * @param msg
     */
    @Override
    public void rabbitMQSendStr(String msg) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("my-queue", System.nanoTime() + "__" + i);
            System.out.println(i);
            Thread.sleep(1000);
        }
    }

    /**
     * 发送对象,对象必须实现序列化接口
     *
     * @param user
     */
    @Override
    public void rabbitMQSendObj(User user) {
        this.rabbitTemplate.convertAndSend("object", user);
    }


}