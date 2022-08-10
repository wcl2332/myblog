package com.wangcl.myblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchenglong
 * @Date: 2022/4/27 16:02
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {
    /**
     * 当前页
     */
    private Long current;
    /**
     * 总数
     */
    private Long total;
    /**
     *
     */
    private Long pages;
    /**
     *
     */
    private Long size;
    /**
     * 数据
     */
    private Object result;


}