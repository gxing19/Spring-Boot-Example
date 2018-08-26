package com.springboot.websocket.entity;

/**
 * @Name: WebSocketMessage
 * @Desc: 接收WebSocket客户端的消息
 * @User: gxing
 * @Date: 2018-08-21 11:18
 **/
public class ClientMsg {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public ClientMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}