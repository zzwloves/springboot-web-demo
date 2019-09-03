package com.example.demo.service;

import com.example.demo.entity.User;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 11:42
 */
public interface UserService {

    User getByToken(String token);
}
