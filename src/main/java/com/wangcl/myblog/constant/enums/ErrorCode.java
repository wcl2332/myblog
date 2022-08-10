package com.wangcl.myblog.constant.enums;

/**
 * 错误状态码
 */
public enum ErrorCode {
    /**
     * 用户名或密码不存在
     */
    Login_NOT_EXIE(100, "用户名或密码不存在"),
    Login_PWD_ERROR(101, "用户名或密码错误"),
    Login_TOKEN_ERROR(102, "登录失效"),
    Parms_ERROR(104, "提交成参数有误"),
    ACCOUNT_EXIST(105, "账户已存在"),
    NO_LOGIN(106, "token为空"),
    ACCOUNT_NOT_EXIST(107, "用户不存在"),
    Token_IS_ERROR(108, "token无效"),
    Article_NOT_EXIST(109, "文章不存在");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
