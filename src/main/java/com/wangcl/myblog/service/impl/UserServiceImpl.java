package com.wangcl.myblog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangcl.myblog.constant.enums.ErrorCode;
import com.wangcl.myblog.mapper.UserMapper;
import com.wangcl.myblog.model.entity.User;
import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.model.vo.UserVO;
import com.wangcl.myblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    private UserVO copy(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public Result findUserByAccount(String account) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getAccount, account);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }
        UserVO userVO = copy(user);
        return Result.success(userVO);
    }

    @Override
    public User findUserById(Long id) {
        User user = userMapper.selectById(id);
        return user;
    }
}
