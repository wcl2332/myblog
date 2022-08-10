package com.wangcl.myblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章
 *
 * @author Wangchenglong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    /**
     * 主键
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 作者 ID,
     */
    private Long authorId;
    /**
     * 文章内容 ID
     */
    private Long contentId;
    /**
     * 封面图片
     */
    private String coverImage;
    /**
     * 文章发布时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 分类ID
     */
    private Long categoryId;
    /**
     * 标签ID
     */
    private Long tagId;
    /**
     * 权重 用于文章置顶
     */
    private Integer weight;
    /**
     * 状态 0 草稿 1 发布
     */
    private Integer statusCode;
    /**
     * 浏览量
     */
    private Integer viewsCount;
    /**
     * 文章列表显示内容, 则 概述
     */
    private String introduction;
}
