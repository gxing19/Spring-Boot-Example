package com.gxitsky;

import lombok.Data;

/**
 * @author gxing
 * @desc 响应
 * @date 2022/3/12
 */
@Data
public class ResponseResult<T> {

    private Integer code = 200;
    private String message = "success";
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(T data) {
        this.data = data;
    }

    public static ResponseResult<?> ok() {
        return new ResponseResult<>();
    }

    public static ResponseResult<?> ok(Object data) {
        return new ResponseResult<>(data);
    }

    public static ResponseResult<?> err() {
        return new ResponseResult<>(400, "系统错误");
    }

    public static ResponseResult<?> err(String message) {
        return new ResponseResult<>(400, message);
    }


}
