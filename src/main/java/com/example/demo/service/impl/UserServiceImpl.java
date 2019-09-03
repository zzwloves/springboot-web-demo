package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 11:44
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

//    @Value("${spring.profiles.active}")
//    private String profile;

    @Override
    public User getByToken(String token) {
        User user = userMapper.selectById("1");
        log.info(user.toString());
        return null;
    }
}
