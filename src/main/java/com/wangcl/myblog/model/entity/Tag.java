package com.wangcl.myblog.model.entity;

import lombok.Data;

@Data
public class Tag {
    private Long id;
    /**
     * 标签名
     */
    private String name;
}
