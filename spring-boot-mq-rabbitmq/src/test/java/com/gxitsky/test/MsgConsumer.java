package com.gxitsky.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MsgConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setHost("192.168.220.139");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        // 创建连接
        Connection connection = factory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明交换器
        String exchangeName = "news-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        String queueName = channel.queueDeclare().getQueue();
//        String queueName = "news-queue";
        // 路由键
        String routingKey = "news-routingKey";
        channel.queueBind(queueName, exchangeName, routingKey);

        while (true) {
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routingKey1 = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("routing key:" + routingKey1);
                    System.out.println("content type:" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    String msg = new String(body, "UTF-8");
                    System.out.println("消息内容:" + msg);

                }
            });
        }
    }
}
