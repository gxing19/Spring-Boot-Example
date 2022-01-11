package com.gxitsky.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class MsgProducer2 {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setHost("192.168.220.139");
        factory.setPort(5672);
        factory.setVirtualHost("/");
//        factory.setUri("amqp://admin:123456@192.168.220.129:5672/");

        // 创建连接
        Connection connection = factory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明交换器
        String exchangeName = "news-exchange";
        // 交换器类型枚举类 BuiltinExchangeType
        channel.exchangeDeclare(exchangeName, "direct", true, false, null);
        // 自动创队列名
//        String queueName = channel.queueDeclare().getQueue();
        // 声明一个持久化,非排他,非自动删除的队列
        String queueName = "news-queue";
        channel.queueDeclare(queueName, true, false, false, null);

        // 路由键
        String routingKey = "news-routingKey";
        // 队列与交换机绑定
        channel.queueBind(queueName, exchangeName, routingKey);

        for (int i = 0; i < 50; i++) {
            //发送消息
            byte[] msgBytes = ("News Msg ......" + i).getBytes();
            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, msgBytes);
            Thread.sleep(10);
        }
        //关闭信道和连接
        channel.close();
        connection.close();

    }
}
