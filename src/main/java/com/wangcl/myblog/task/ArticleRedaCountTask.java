package com.wangcl.myblog.task;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wangcl.myblog.model.entity.Article;
import com.wangcl.myblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 定时器 定时向数据库中固话文章阅读量
 *
 * @Author: Wangchenglong
 * @Date: 2022/4/28 16:41
 * @Description: TODO
 */
@Component
@Slf4j
public class ArticleRedaCountTask {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisTemplate redisTemplate;

    //redis 中文章 id 前缀
    private final static String keys = "article_";

    /***
     * 定时同步redis中文章的访问数量
     * @date: 2022/4/28 16:45
     * @param: []
     * @return: void
     **/
    @Scheduled(cron = "0 0 10,17 * * ?")
    private void setArticleCount() {
        Set<String> keySet = redisTemplate.keys("article_*");
        List<String> articleIds = new ArrayList<String>();
        for (String key : keySet) {
            articleIds.add(key.substring(keys.length()));
        }
        //我们知道需要同步哪些数据  
        for (String key : articleIds) {
            LambdaUpdateWrapper<Article> articleLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            articleLambdaUpdateWrapper.eq(Article::getId, key).set(Article::getViewsCount, redisTemplate.opsForValue().get(keys + key));
            articleService.update(articleLambdaUpdateWrapper);
        }
    }
}