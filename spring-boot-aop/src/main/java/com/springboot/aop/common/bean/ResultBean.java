package com.springboot.aop.common.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultBean<T> {

    public ResultBean() {
    }

    public ResultBean(boolean state, Integer code, String msg) {
        this.state = state;
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(boolean state, Integer code, String msg, T data) {
        this.state = state;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private boolean state;
    private Integer code;
    private String msg;
    private T data;

}
