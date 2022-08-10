package com.wangcl.myblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2022/4/28 11:53
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "article_content")
public class ArticleBody {
    /**
     * id
     */
    private Integer id;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 文章内容
     */
    private String content;
}