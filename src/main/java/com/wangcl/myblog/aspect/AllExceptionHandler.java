package com.wangcl.myblog.aspect;

import com.wangcl.myblog.model.vo.Result;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Wangchenglong
 */
@ControllerAdvice
public class AllExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AllExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception e) {
        e.printStackTrace();
        logger.info("系统异常：{}", e.getMessage());
        return Result.fail(-99, "系统繁忙");
    }
}
