package com.wangcl.myblog.service;

import com.wangcl.myblog.model.entity.Category;

public interface CategoryService {
    Category findCategoryById(Long id);
}
