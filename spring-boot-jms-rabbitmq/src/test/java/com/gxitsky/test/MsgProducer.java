package com.gxitsky.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MsgProducer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setHost("192.168.220.129");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        // 创建连接
        Connection connection = factory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明交换器
        String exchangeName = "news-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);
        // 路由键
        String routingKey = "news-routingKey";

        for (int i = 0; i < 50; i++) {
            //发送消息
            byte[] msgBytes = ("News Msg ......" + i).getBytes();
            channel.basicPublish(exchangeName, routingKey, null, msgBytes);
            Thread.sleep(2000);
        }
        //关闭信道和连接
        channel.close();
        connection.close();

    }
}
