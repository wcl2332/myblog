package com.wangcl.myblog.model.vo;

import com.wangcl.myblog.model.entity.ArticleBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章详细内容
 * @Author: Wangchenglong
 * @Date: 2022/4/28 11:34
 * @Description: TODO
 */
@Data
public class ArticleDetailVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 作者名
     */
    private String authorName;
    /**
     * 文章内容
     */
    private ArticleBody articleBody;
    /**
     * 文章发布时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 浏览量
     */
    private Integer viewsCount;

}