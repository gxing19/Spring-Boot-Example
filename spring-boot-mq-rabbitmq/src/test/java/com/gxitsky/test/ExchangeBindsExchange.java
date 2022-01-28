package com.gxitsky.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class ExchangeBindsExchange {
    public static void main(String[] args) throws IOException, TimeoutException {

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
        connection.
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

        String exKey = "exKey";
        // 声明2个交换器
        channel.exchangeDeclare("source", "direct", false, true, null);
        channel.exchangeDeclare("destination", "fanout", false, true, null);
        // 2个交换器绑定
        channel.exchangeBind("destination", "source", "exKey");
        // 声明队列
        channel.queueDeclare(queueName, true, false, false, null);
        // 队列绑定到目标交换器
        channel.queueBind(queueName, "destination", "");
        // 消息发送到源交换器
        channel.basicPublish("source", "exKey", null, "msg content".getBytes());
        channel.basicPublish("exchange", "routeKey", true, true, null, "".getBytes());


        SortedSet<Long> confirmSet = new TreeSet<>();
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("Ack, SeqNo:" + deliveryTag + ", multiple:" + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("NAck, SeqNo:" + deliveryTag + ", multiple:" + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
                //注意,这里需要添加处理消息重发的场景
            }
        });

        while (true){
            long nextSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("exchange.name", "routing.key", MessageProperties.PERSISTENT_TEXT_PLAIN, "msg".getBytes());
            confirmSet.add(nextSeqNo);
        }


    }
}

