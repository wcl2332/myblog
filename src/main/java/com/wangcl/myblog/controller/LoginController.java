package com.wangcl.myblog.controller;

import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.model.vo.parms.LoginParms;
import com.wangcl.myblog.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户登录")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestParam(value = "loginName") String loginName, @RequestParam(value = "passWord") String passWord) {
        return loginService.login(loginName, passWord);
    }
}
