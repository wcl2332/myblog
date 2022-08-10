package com.wangcl.myblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户
 *
 * @author Wangchenglong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 账户
     */
    private String account;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 简介
     */
    private String summary;
    /**
     * 创建时间
     */
    private String creatTime;
    /**
     * 最近登录时间
     */
    private String lastTime;
}
