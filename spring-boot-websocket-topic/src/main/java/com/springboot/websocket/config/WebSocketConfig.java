package com.springboot.websocket.config;

import com.springboot.websocket.common.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * @Name: WebSocketConfig
 * @Desc: WebSocket配置
 * @User: gxing
 * @Date: 2018-08-10 11:55
 **/
@Configuration
@EnableWebSocketMessageBroker   //开启使用STOMP协议来传输基于代理(message broker)的消息
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{               //v2.0.x
//public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{        //v1.5.x

    //注册 WebSocketp客户端连接端点(路径)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //"portfolio"是WebSocket（或SockJS）客户端为了进行WebSocket握手而需要连接的端点的HTTP URL。
        //并指定使用SocketJS协议
        registry.addEndpoint("/websocket")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*")
                .withSockJS();
    }

    //配置消息代理(MessageBroker)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //目标路径"/app"开头的STOMP消息将路由到@Controller类中的@MessageMapping方法。
        config.setApplicationDestinationPrefixes("/app");

        //使用内置的消息代理进行订阅和广播; 将目标标头以“/ topic”或“/ queue”开头的消息路由到代理。
        //"/topic"广播; "/queue"订阅
        config.enableSimpleBroker("/topic");
//        config.enableSimpleBroker("/topic", "/queue");
    }

}