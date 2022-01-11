package com.gxitsky.service.impl;

import com.gxitsky.service.MQProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;

/**
 * @name: MQSendServiceImpl
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-18 14:07
 **/
@Service
public class MQProducerServiceImpl implements MQProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送queue消息
     *
     * @param msg
     * @throws JMSException
     */
    @Override
    public void activeMQSend(String msg) throws JMSException {

        MessageCreator messageCreator = session -> session.createTextMessage(msg);

        //发布queue
        Destination queueDestination = new ActiveMQQueue("my-queue");
        jmsTemplate.send(queueDestination, messageCreator);
        jmsTemplate.convertAndSend(queueDestination, "Hello Queue");

        //发布topic
        Destination topicDestination = new ActiveMQTopic("my-topic");
        jmsTemplate.send(topicDestination, messageCreator);
        jmsTemplate.convertAndSend(queueDestination, "Hello Topic");

    }
}