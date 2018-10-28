package com.springboot.rabbitmq.service;

import com.springboot.rabbitmq.entity.User;

public interface TopicProducerService {

    void rabbitMQSendStr(String msg);

    void rabbitMQSendObj(User andy);

    /*void send();*/
}
