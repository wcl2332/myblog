package com.wangcl.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangcl.myblog.constant.enums.ErrorCode;
import com.wangcl.myblog.mapper.ArticleBodyMapper;
import com.wangcl.myblog.mapper.ArticleMapper;
import com.wangcl.myblog.model.entity.Article;
import com.wangcl.myblog.model.entity.ArticleBody;
import com.wangcl.myblog.model.entity.Category;
import com.wangcl.myblog.model.entity.Tag;
import com.wangcl.myblog.model.vo.*;
import com.wangcl.myblog.service.ArticleService;
import com.wangcl.myblog.service.CategoryService;
import com.wangcl.myblog.service.TagService;
import com.wangcl.myblog.service.UserService;
import com.wangcl.myblog.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleBodyMapper articleBodyMapper;
    @Autowired
    private RedisUtil redisUtil;

    private final Integer delta = 1;

    /***
     * 文章首页列表
     * @date: 2022/4/28 9:56
     * @param: [page, size, authorid]
     * @return: com.wangcl.myblog.model.vo.Result
     *
     **/
    @Override
    public Result findArticleAll(Integer page, Integer size, String authorid) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getAuthorId, authorid);
        Page<Article> articlePage = articleMapper.selectPage(new Page<>(page, size), queryWrapper);
        List<Article> articlePageRecords = articlePage.getRecords();
        List<ArticleListVO> articleVos = copyList(articlePageRecords);
        PageVO pagevo = new PageVO();
        pagevo.setPages(articlePage.getPages());
        pagevo.setCurrent(articlePage.getCurrent());
        pagevo.setSize(articlePage.getSize());
        pagevo.setTotal(articlePage.getTotal());
        pagevo.setResult(articleVos);
        return Result.success(pagevo);
    }

    /***
     * 获取文章详细内容
     * @date: 2022/4/28 11:45
     * @param: [id]
     * @return: com.wangcl.myblog.model.vo.Result
     **/
    @Override
    public Result findArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            return Result.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }
        ArticleDetailVO articleDetailVO = new ArticleDetailVO();
        BeanUtils.copyProperties(article, articleDetailVO);
        String name = userService.findUserById(article.getAuthorId()).getRealName();
        articleDetailVO.setAuthorName(name);
        ArticleBody articleBody = new ArticleBody();
        if (article.getContentId() != null) {
            articleBody = findTagByarticleId(article.getContentId());
            articleDetailVO.setArticleBody(articleBody);
        }
        redisUtil.incr("article_" + id, delta);
        return Result.success(articleDetailVO);
    }

    /**
     * 文章归档
     *
     * @param authorid
     * @return
     */
    @Override
    public Result findArticleFile(String authorid) {
        List<String> groupYear = articleMapper.findGroupYear(authorid);
        Map<String, List<ArticleFileVO>> listMap = new HashMap<>();
        for (String year : groupYear) {
            List<Article> articleByYear = articleMapper.findArticleByYear(authorid,year);
            listMap.put(year, copyList1(articleByYear));
        }
        return Result.success(listMap);
    }

    private List<ArticleListVO> copyList(List<Article> list) {
        ArrayList<ArticleListVO> articleVos = new ArrayList<>();
        for (Article article : list) {
            articleVos.add(copy(article));
        }
        return articleVos;
    }


    private List<ArticleFileVO> copyList1(List<Article> list) {
        ArrayList<ArticleFileVO> articleVos = new ArrayList<>();
        for (Article article : list) {
            articleVos.add(copyProperties(article));
        }
        return articleVos;
    }

    private ArticleFileVO copyProperties(Article article) {
        ArticleFileVO articleFileVO = new ArticleFileVO();
        BeanUtils.copyProperties(article, articleFileVO);
        return articleFileVO;
    }

    private ArticleListVO copy(Article article) {
        ArticleListVO articleVO = new ArticleListVO();
        BeanUtils.copyProperties(article, articleVO);
        Tag tag = tagService.findTagById(article.getTagId());
        articleVO.setTag(tag);
        Category category = categoryService.findCategoryById(article.getCategoryId());
        articleVO.setCategory(category);
        return articleVO;
    }

    /**
     * 根据id获取文章内容
     *
     * @param articleId
     * @return
     */
    private ArticleBody findTagByarticleId(Long articleId) {
        LambdaQueryWrapper<ArticleBody> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleBody::getArticleId, articleId);
        ArticleBody articleBody = articleBodyMapper.selectOne(queryWrapper);
        return articleBody;
    }

}
