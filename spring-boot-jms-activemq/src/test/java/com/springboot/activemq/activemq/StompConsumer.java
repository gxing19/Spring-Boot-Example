package com.springboot.activemq.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;

public class StompConsumer {

    public static void main(String[] args) {
        StompConnection connection = new StompConnection();

        try {
            connection.open("47.104.85.219", 61613);
            connection.connect(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD);
            connection.subscribe("/topic/shopping-discount");
            while (true) {
                StompFrame receive = connection.receive();
                String body = receive.getBody();
                System.out.println("收到 Stomp 消息：" + body);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
