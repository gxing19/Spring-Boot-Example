package com.gxitsky.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;

import javax.jms.*;
import java.util.Properties;

public class MQProducer {

    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616?jms.prefetchPolicy.all=50");
        try {
            ActiveMQPrefetchPolicy prefetchPolicy = ((ActiveMQConnectionFactory) connectionFactory).getPrefetchPolicy();

            //创建连接
            Connection connection = connectionFactory.createConnection();
//            ActiveMQPrefetchPolicy prefetchPolicy2 = ((ActiveMQConnection) connection).getPrefetchPolicy();

//            Properties properties = new Properties();
//            properties.setProperty("prefetchPolicy.queuePrefetch", "1000");
//            properties.setProperty("prefetchPolicy.topicPrefetch", "1000");
//            ((ActiveMQConnectionFactory) connectionFactory).setProperties(properties);

//            prefetchPolicy.setAll(1000);
//            prefetchPolicy.setQueuePrefetch(1000);
//            prefetchPolicy.setTopicPrefetch(1000);



            //开启连接
            connection.start();
            //创建全话,不启用事务,使用自动确认应答
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //创建消息
            TextMessage message = session.createTextMessage("Hello Word!");

            //创建主题
            Topic topic = session.createTopic("activemq-topic-test1");
            //创建生产者
            MessageProducer topicProducer = session.createProducer(topic);
            topicProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //发送消息
            topicProducer.send(message);

            Queue queue = session.createQueue("activemq-queue-test1");
            MessageProducer queueProducer = session.createProducer(queue);
            queueProducer.send(message);

            //关闭资源
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
