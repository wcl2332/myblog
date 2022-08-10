package com.wangcl.myblog.service.impl;

import com.wangcl.myblog.mapper.CategoryMapper;
import com.wangcl.myblog.model.entity.Category;
import com.wangcl.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Wangchenglong
 * @Date: 2022/4/28 11:22
 * @Description: TODO
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findCategoryById(Long id) {
        Category category = categoryMapper.selectById(id);
        return category;
    }
}