package com.wangcl.myblog.controller;

import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户信息")
    @GetMapping("/userInfo/{id}")
    public Result getUserInfo(@PathVariable("id") String account) {
        return userService.findUserByAccount(account);
    }



}
