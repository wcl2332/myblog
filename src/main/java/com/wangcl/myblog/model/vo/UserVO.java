package com.wangcl.myblog.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    private String name;
    private String avatar;
    private Date lastTime;
    private String summary;
}
