package com.example.demo.base;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/9/25 15:23
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnNotProdEnvCondition.class)
public @interface ConditionalOnNotProdEnv {
}
