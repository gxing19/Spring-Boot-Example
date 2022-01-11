package com.gxitsky.configure;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * @name: MQComponent
 * @desc: 消息组件
 * @author: gxing
 * @date: 2018-10-18 14:47
 **/
@Configuration
public class ActiveMQConfig {

    /**
     * 一个项目里若要同时使用 Queue队列 和 Topic主题(发布-订阅)
     * 需要为 Topic主题再定义一个JmsListenerContainerFactory,开启发布-订阅
     *
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();
        topicListenerContainer.setPubSubDomain(true);
        topicListenerContainer.setConnectionFactory(activeMQConnectionFactory);
        return topicListenerContainer;
    }

}