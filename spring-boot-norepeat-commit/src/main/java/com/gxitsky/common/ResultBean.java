package com.gxitsky.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultBean<T> {

    private int code;
    private String msg;
    private T data;

    public ResultBean(T data) {
        this.code = 200;
        this.msg = "SUCCESS";
        this.data = data;
    }
}
