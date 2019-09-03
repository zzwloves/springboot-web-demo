package com.example.demo.base;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 10:20
 */
public enum ExceptionEnum {

    SYSTEM_ERROR(5000, "系统未知异常"),
    PARAM_ERROR(4001, "参数错误");


    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}