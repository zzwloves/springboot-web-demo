package com.example.demo.controller;

import com.example.demo.base.Group;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 09:56
 */
@Api(value = "测试接口controller")
@ApiOperation(value = "类")
@RestController
public class TestController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "测试接口")
    @GetMapping("test")
    public String test() {
        userService.getByToken(null);
        return "success";
    }

    @ApiOperation(value = "post测试接口")
    @PostMapping("test")
    public String post(@Validated(Group.Post.class) @RequestBody User user) {
        userService.getByToken(null);
        return "success";
    }
}
