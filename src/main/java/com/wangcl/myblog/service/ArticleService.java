package com.wangcl.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangcl.myblog.model.entity.Article;
import com.wangcl.myblog.model.vo.Result;

public interface ArticleService extends IService<Article> {
    /**
     *
     * @param page 页面
     * @param size 每页显示的条数
     * @param authorid 作者ID
     * @return
     */
    Result findArticleAll(Integer page, Integer size, String authorid);

    Result findArticleById(Long id);

    Result findArticleFile(String authorid);
}
