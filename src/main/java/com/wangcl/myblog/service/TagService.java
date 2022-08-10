package com.wangcl.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangcl.myblog.model.entity.Tag;

/**
 * @Author: Wangchenglong
 * @Date: 2022/4/27 16:37
 * @Description: TODO
 */
public interface TagService {
    Tag findTagById(Long id);
}