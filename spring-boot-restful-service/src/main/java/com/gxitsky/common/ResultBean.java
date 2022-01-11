package com.gxitsky.common;

import java.io.Serializable;

/**
 * @desc: 统一响应消息结构体
 * @author: gxing
 * @date: 2019/1/30 11:26
 * @param:
 * @return:
 **/
public class ResultBean implements Serializable {

    private static final long serialVersionUID = -8332309757143905140L;

    private static final boolean SUCCESS = true;
    private static final boolean FAIL = false;

    private Boolean state;
    private Integer code;
    private String msg;
    private Object data;

    public ResultBean successResult() {
        this.state = SUCCESS;
        return this;
    }

    public ResultBean failResult() {
        this.state = FAIL;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public ResultBean setState(Boolean state) {
        this.state = state;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResultBean setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultBean setData(Object data) {
        this.data = data;
        return this;
    }
}
