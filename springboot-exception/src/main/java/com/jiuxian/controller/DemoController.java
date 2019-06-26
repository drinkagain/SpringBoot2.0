package com.jiuxian.controller;

import com.jiuxian.web.ResponseResult;
import com.jiuxian.exception.BaseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: LIU ZEJUN
 * Date: 2019-03-05 19:36:00
 * Comment:
 */

@RestController
public class DemoController {

    @GetMapping(value = "/test")
    public ResponseResult<String> test(String a) {
        if ("a".equals(a)) throw new BaseException("失败");
        return new ResponseResult<>("Hello!");

    }

}
