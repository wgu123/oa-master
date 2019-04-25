package com.wgu.global;

import com.wgu.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: w
 * @Date: 2019/4/25 11:25
 * @Version 1.0
 */
//全局异常处理
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常返回
     * ExceptionHandler 统一处理某一类异常，从而能够减少代码重复率和复杂度
     */
    @ExceptionHandler(MyException.class)
    public Result myException(MyException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return new Result(e.getCode(), e.getMessage());
    }

    /**
     * 未捕捉异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return new Result(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
