package com.jiuxian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootInterceptorApplication.class, args);
    }


    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() throws InterruptedException {
        Thread.sleep(500);
        return ResponseEntity.ok("HelloWorld");
    }

    @GetMapping(value = "/hello2")
    public ResponseEntity<String> hello2() throws InterruptedException {
        Thread.sleep(500);
        return ResponseEntity.ok("HelloWorld2");
    }
}

