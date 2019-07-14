package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: lenovo
 * @Date: 2019-07-13 22:46
 * @Description:
 */
@Slf4j
@ControllerAdvice   //统一异常处理
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error(e.getMessage());
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
