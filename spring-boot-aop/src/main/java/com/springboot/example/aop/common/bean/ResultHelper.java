package com.springboot.example.aop.common.bean;

public class ResultHelper {

    public static <T> ResultBean<T> success(){
        return success(null);
    }

    public static <T> ResultBean<T> success(T data){
        return new ResultBean<T>(true, 200, "success", data);
    }

    public static <T> ResultBean<T> fail(){
        return new ResultBean<T>(false, 400, "failure");
    }

    public static <T> ResultBean<T> sysError(){
        return new ResultBean<T>(false, 401, "System Internal Error");
    }

    public static <T> ResultBean<T> repeatCommit(){
        return new ResultBean<T>(false, 402, "请不要重复提交!");
    }
}
