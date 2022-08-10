package com.wangcl.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangcl.myblog.model.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    List<String> findGroupYear(String authorid);

    List<Article> findArticleByYear(String authorid,String year);
}
