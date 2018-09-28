package com.springboot.template.common;

import java.io.Serializable;


/**
 * @ClassName: 统一响应结果
 * @Description: 响应结果的编码和消息
 * @User: gxing
 * @Date: 2018-04-20 12:16
 **/
public class ResultBean implements Serializable {

    private static final long serialVersionUID = -8332309757143905140L;

    private static final String STATE_SUCCESS = "success";
    private static final String STATE_FAIL = "fail";
    private static final Integer CODE_SUCCESS = 200;
    private static final Integer CODE_FAIL = 400;

    private Integer code;               //编码
    private String state;               //状态
    private String msg;                 //返回消息
    private Object data;                //数据

    public ResultBean() {
    }

    public ResultBean(Object data) {
        this.data = data;
    }

    public ResultBean(String msg) {
        this.msg = msg;
    }

    public ResultBean(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(Integer code, String state, String msg, Object data) {
        this.code = code;
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ResultBean setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getState() {
        return state;
    }

    public ResultBean setSuccessCodeAndState() {
        this.code = CODE_SUCCESS;
        this.state = STATE_SUCCESS;
        return this;
    }

    public ResultBean setFailState() {
        this.state = STATE_FAIL;
        return this;
    }

    public ResultBean setFailCodeAndState() {
        this.code = CODE_FAIL;
        this.state = STATE_FAIL;
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

    public ResultBean setCodeAndMsg(ResultCodeAndMsg codeAndMsg) {
        this.code = codeAndMsg.getCode();
        this.msg = codeAndMsg.getMsg();
        return this;
    }
}
