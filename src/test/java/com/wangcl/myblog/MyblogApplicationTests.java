package com.wangcl.myblog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangcl.myblog.mapper.UserMapper;
import com.wangcl.myblog.model.entity.User;
import com.wangcl.myblog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.Date;
import java.util.List;

@SpringBootTest
class MyblogApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("key123","1211");
    }

    @Test
    public void  testQueryMapper(){
        String name="18855060689";
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name",name);
        User user = userMapper.selectOne(userQueryWrapper);
        System.out.println(user);
    }

    @Test
    public void testQueryMapper1(){
        String name="18855060689";
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(name),User::getAccount,name);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        System.out.println(user);
    }
    
    @Test
    public void updateMapper(){
        String name="13282802580";
        LambdaUpdateWrapper<User> objectLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        objectLambdaUpdateWrapper.eq(User::getAccount,name).set(User::getLastTime,new Date());
        User user=new User();
        int update = userMapper.update(new User(), objectLambdaUpdateWrapper);
        System.out.println(update);
        
    }


}
