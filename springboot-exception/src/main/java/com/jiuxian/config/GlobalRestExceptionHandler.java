package com.jiuxian.config;

import com.jiuxian.web.ResponseResult;
import com.jiuxian.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author: LIU ZEJUN
 * Date: 2019-03-05 19:25:00
 * Comment:
 */

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ResponseResult baseException(BaseException e) {
        return new ResponseResult<>(e.getCode(), e.getMessage());
    }
}
