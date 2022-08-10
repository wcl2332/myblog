package com.wangcl.myblog.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * 文章归档
 * @Author: Wangchenglong
 * @Date: 2022/5/6 10:01
 * @Description: TODO
 */
@Data
public class ArticleFileVO {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Date createTime;


}