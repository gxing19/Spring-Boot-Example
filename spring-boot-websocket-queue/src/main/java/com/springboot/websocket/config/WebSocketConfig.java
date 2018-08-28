package com.springboot.websocket.config;

import com.springboot.websocket.common.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Name: WebSocketConfig
 * @Desc: WebSocket配置
 * @User: gxing
 * @Date: 2018-08-10 11:55
 **/

@Configuration
@EnableWebSocketMessageBroker   //开启使用STOMP协议来传输基于代理(message broker)的消息
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //注册 WebSocketp客户端连接端点(路径)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个WebSocket（或SockJS）客户端进行WebSocket握手而需要连接的端点, 使用SockJS协议。
        registry.addEndpoint("/endpointChat")
                .addInterceptors(new WebSocketInterceptor())
                .withSockJS();
    }

    //配置消息代理(MessageBroker)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //目标路径"/im"开头的STOMP消息将路由到@Controller类中的@MessageMapping方法;
        //此处若配置了, 则前端客户端发送消息的路径需加上前缀作为开头
        registry.setApplicationDestinationPrefixes("/im");

        //使用内置的消息代理进行订阅和广播; 将目标标头以“/ topic”或“/ queue”开头的消息路由到代理。
        //"/topic"广播; "/queue"点对点
        registry.enableSimpleBroker("/queue");

        //自定义发送消息路径前缀, 服务端使用SimpMessagingTemplate发送消息的路径默认前缀是/user, 客户端订阅消息的路径必须加上此前缀
//        registry.setUserDestinationPrefix("/msg");

    }

}
