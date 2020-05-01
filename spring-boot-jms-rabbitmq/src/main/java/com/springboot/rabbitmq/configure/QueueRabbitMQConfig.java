package com.springboot.rabbitmq.configure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @name: MQComponent
 * @desc: 消息组件
 * @author: gxing
 * @date: 2018-10-18 14:47
 **/
@Configuration
public class QueueRabbitMQConfig {

    private static final String QUEUE_NAME_1 = "my-queue";
    private static final String QUEUE_NAME_2 = "object";

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue strQueue() {
        return new Queue(QUEUE_NAME_1);
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue objQueue() {
        return new Queue(QUEUE_NAME_2);
    }

    /**
     * 声明交换器
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = new DirectExchange("direct.exchange", false, true);
        return directExchange;
    }

    /**
     * 队列绑定交换器
     *
     * @param strQueue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding strQueueBind(Queue strQueue, DirectExchange directExchange) {
        //使用队列名作为路由
        Binding binding = BindingBuilder.bind(strQueue).to(directExchange).withQueueName();
        return binding;
    }

    @Bean
    public Binding objQueueBind(Queue objQueue, DirectExchange directExchange) {
        //使用队列名作为路由
        Binding binding = BindingBuilder.bind(objQueue).to(directExchange).withQueueName();
        return binding;
    }

}