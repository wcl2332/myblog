package com.wangcl.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangcl.myblog.model.entity.User;
import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.model.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface UserService extends IService<User>{

    Result findUserByAccount(String account);

    User findUserById(Long id);

}
