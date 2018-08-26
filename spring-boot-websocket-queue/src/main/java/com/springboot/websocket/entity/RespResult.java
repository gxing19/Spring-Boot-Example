package com.springboot.websocket.entity;

/**
 * @Name: ResposeMessage
 * @Desc: 服务器响应消息
 * @User: gxing
 * @Date: 2018-08-21 11:19
 **/
public class RespResult {

    private String resultMsg;

    public RespResult(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public RespResult setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
        return this;
    }
}