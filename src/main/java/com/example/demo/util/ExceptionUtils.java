package com.example.demo.util;

import com.example.demo.base.BusinessException;
import com.example.demo.base.ExceptionEnum;

/**
 * 异常工具类
 *
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 17:42
 */
public class ExceptionUtils {

    /**
     * 创建异常并抛出
     *
     * @param ee 异常信息枚举
     */
    public static void throwException(ExceptionEnum ee) {
        throwException(true, ee);
    }

    /**
     * 根据条件判断，条件为true则创建异常并抛出
     *
     * @param ee 异常信息枚举
     */
    public static void throwException(boolean condition, ExceptionEnum ee) {
        if (condition) {
            throw new BusinessException(ee);
        }
    }
}
