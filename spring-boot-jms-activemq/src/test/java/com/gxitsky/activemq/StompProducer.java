package com.gxitsky.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.transport.stomp.StompConnection;

public class StompProducer {

    public static void main(String[] args) {
        StompConnection connection = new StompConnection();
        try {
            connection.open("47.104.85.219", 61613);
            connection.connect(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD);
            String message = "好货半价, 速来抢购";
            connection.send("/topic/shopping-discount", message);
            connection.disconnect();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
