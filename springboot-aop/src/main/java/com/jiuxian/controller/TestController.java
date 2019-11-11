package com.jiuxian.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zejun.liu
 * *
 * @date: 2019/11/11 10:08
 * *
 * @description:
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("OK");
    }
}
