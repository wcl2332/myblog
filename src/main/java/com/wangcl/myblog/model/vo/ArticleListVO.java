package com.wangcl.myblog.model.vo;

import com.wangcl.myblog.model.entity.Category;
import com.wangcl.myblog.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVO {

    private Long Id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章发布时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 文章封面图片
     */
    private String coverImage;
    /**
     * 文章列表显示内容, 则 概述
     */
    private String introduction;
    /**
     *
     */
    private Tag tag;
    /**
     *
     */
    private Category category;
    /**
     * 权重 用于文章置顶
     */
    private Integer weight;
}
