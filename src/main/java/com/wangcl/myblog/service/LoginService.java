package com.wangcl.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangcl.myblog.model.entity.User;
import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.model.vo.parms.LoginParms;
import com.wangcl.myblog.model.vo.parms.RegisterParms;

public interface LoginService  {
    Result register(String loginName,String passWord);

    User checkToken(String token);

    Result login(String loginName,String passWord);
}
