package com.wangcl.myblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wangcl.myblog.constant.enums.ErrorCode;
import com.wangcl.myblog.mapper.UserMapper;
import com.wangcl.myblog.model.entity.User;
import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.model.vo.parms.LoginParms;
import com.wangcl.myblog.model.vo.parms.RegisterParms;
import com.wangcl.myblog.service.LoginService;
import com.wangcl.myblog.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录
 *
 * @author Wangchenglong
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    private final String KEY = "123456789_abcde_";

    @Override
    public Result register(String loginName, String password) {
        //判断提交参数是否存在问题
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.Parms_ERROR.getCode(), ErrorCode.Parms_ERROR.getMsg());
        }
        //查询数据库中是否存在
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount, loginName);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user != null) {
            //当前用户存在
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        user = new User();
        user.setAccount(loginName);
        user.setPassword(DigestUtils.md5Hex(KEY + password));
        user.setAvatar("/static/image/avatar.png");
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setCreatTime(ft.format(dNow));
        userMapper.insert(user);
        String token = JWTUtils.createToken(user);
        return Result.success(token);
    }

    @Override
    public User checkToken(String token) {
        Map<String, Object> map = new HashMap<>();
        map = JWTUtils.checkToken(token);
        Integer id = Integer.parseInt(String.valueOf(map.get("id")));
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public Result login(String loginName,String password) {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.Login_PWD_ERROR.getCode(), ErrorCode.Login_PWD_ERROR.getMsg());
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount, loginName);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }

        if (!user.getPassword().equals(DigestUtils.md5Hex(KEY + password))) {
            return Result.fail(ErrorCode.Login_PWD_ERROR.getCode(), ErrorCode.Login_PWD_ERROR.getMsg());
        }
        //生成了一个token,保存到缓存中
        String token = JWTUtils.createToken(user);
        //redisTemplate.opsForValue().set("Token_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
        if (token.length() > 0) {
            LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            userLambdaUpdateWrapper.eq(User::getAccount, loginName).set(User::getLastTime, new Date());
            int update = userMapper.update(user, userLambdaUpdateWrapper);
        }
        return Result.success(token);
    }
}
