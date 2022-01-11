package com.gxitsky.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQTransaction {

    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

        try {
            //创建连接
            Connection connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建全话,不启用事务,使用自动确认应答
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Topic txTopic = session.createTopic("activemq-topic-tx-test1");
            MessageProducer producer = session.createProducer(txTopic);

            for (int i = 0; i < 10; i++) {
                TextMessage message = session.createTextMessage("发送消息:" + 1);
                producer.send(txTopic, message);
                //每发送10条消息就提交事务
                if((i % 10) == 0){
                    session.commit();
                }

            }

            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
