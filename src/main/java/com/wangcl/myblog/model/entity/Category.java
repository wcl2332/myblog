package com.wangcl.myblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
     * 分类编号
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;
}
