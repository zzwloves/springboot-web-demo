package com.example.demo.base;

import com.example.demo.base.ExceptionEnum;
import lombok.*;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 10:18
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private Integer code;
    private String msg;

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ExceptionEnum ee) {
        this.code = ee.getCode();
        this.msg = ee.getMsg();
    }
}
