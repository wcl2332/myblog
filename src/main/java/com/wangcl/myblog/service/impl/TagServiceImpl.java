package com.wangcl.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangcl.myblog.mapper.TagMapper;
import com.wangcl.myblog.model.entity.Tag;
import com.wangcl.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author: Wangchenglong
 * @Date: 2022/4/27 16:58
 * @Description: TODO
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag findTagById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return tag;
    }
}