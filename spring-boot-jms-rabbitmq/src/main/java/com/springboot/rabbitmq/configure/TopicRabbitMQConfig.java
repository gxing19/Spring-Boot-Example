package com.springboot.rabbitmq.configure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @name: TopicRabbitMQConfig
 * @desc: Topic 配置
 * @author: gxing
 * @date: 2018-10-26 11:26
 **/
@Configuration
public class TopicRabbitMQConfig {

    //新闻主题
    private static final String TOPIC_NEWS = "topic.news";
    //NBA新闻主题
    private static final String TOPIC_NEWS_NBA = "topic.news.nba";

    @Bean
    public Queue queueNews() {
        return new Queue(TopicRabbitMQConfig.TOPIC_NEWS);
    }

    @Bean
    public Queue queueNewsNba() {
        return new Queue(TopicRabbitMQConfig.TOPIC_NEWS_NBA);
    }

    /**
     * 注册交换器
     *
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        TopicExchange exchange = new TopicExchange("exchange");
        return exchange;
    }

    /**
     * 队列绑定到交换器,设置匹配的routing_key
     * /
     * <p>
     * /**
     * 订阅新闻主题可以收到所有新闻包括NBA
     *
     * @param queueNews
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeNews(Queue queueNews, TopicExchange exchange) {
        return BindingBuilder.bind(queueNews).to(exchange).with("topic.news.#");
    }

    /**
     * 订阅新闻下的NBA主题只可以收到NBA新闻
     *
     * @param queueNewsNba
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeNewsNba(Queue queueNewsNba, TopicExchange exchange) {
        return BindingBuilder.bind(queueNewsNba).to(exchange).with("topic.news.nba");
    }
}