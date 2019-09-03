package com.example.demo.base;

import lombok.*;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 10:26
 */
@Getter
@Setter
@ToString
public class ResponseResult {
    private static final Integer SUCCESS_CODE = 2000;
    private static final String SUCCESS_MSG = "SUCCESS";

    private Integer code;
    private String msg;
    private Object data;
    private Object extraData;

    public ResponseResult() {
    }

    public ResponseResult(Object data) {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }
}
