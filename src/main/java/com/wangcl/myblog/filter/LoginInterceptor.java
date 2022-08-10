package com.wangcl.myblog.filter;


import com.alibaba.fastjson.JSON;
import com.wangcl.myblog.constant.enums.ErrorCode;
import com.wangcl.myblog.model.entity.User;
import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        if (token == null) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            logger.info("result：{}",result.toString());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        User user = loginService.checkToken(token);
        logger.info("user",user.toString());
        if (user == null) {
            Result result = Result.fail(ErrorCode.Token_IS_ERROR.getCode(), ErrorCode.Token_IS_ERROR.getMsg());
            logger.info("result：{}",result.toString());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        return true;
    }
}
