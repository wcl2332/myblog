package com.wangcl.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangcl.myblog.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

}
