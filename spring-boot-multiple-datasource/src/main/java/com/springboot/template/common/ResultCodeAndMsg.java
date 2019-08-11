package com.springboot.template.common;

/**
 * @ClassName: ResultCodeAndMsg
 * @Description: 响应结果的编码和消息
 * @User: gxing
 * @Date: 2018-04-20 12:16
 **/
public enum ResultCodeAndMsg {

    /*--错误,失败,异常 code以400开头--*/


    //-------参数检验----------------
    ERR_PARAM_BLANK(4000, "参数为空"),
    ERR_PARAM_EXIST(4001, "参数已存在");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public ResultCodeAndMsg setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultCodeAndMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    ResultCodeAndMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
