package com.gxitsky.service;

import com.gxitsky.entity.User;

public interface TopicProducerService {

    void rabbitMQSendStr(String msg);

    void rabbitMQSendObj(User andy);

    /*void send();*/
}
