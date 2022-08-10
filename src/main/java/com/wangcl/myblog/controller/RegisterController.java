package com.wangcl.myblog.controller;

import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.model.vo.parms.RegisterParms;
import com.wangcl.myblog.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户注册")
@RestController
public class RegisterController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value="注册")
    @PostMapping("/register")
    private Result register(@RequestParam(value = "loginName") String loginName,@RequestParam(value = "passWord") String passWord) {

        return loginService.register(loginName,passWord);
    }
}
