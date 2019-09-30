package com.example.demo.base;

import javax.validation.groups.Default;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/9/2 17:20
 */
public interface Group {

    interface Add extends Default {}

    interface Delete extends Default {}

    interface Update extends Default {}
}
