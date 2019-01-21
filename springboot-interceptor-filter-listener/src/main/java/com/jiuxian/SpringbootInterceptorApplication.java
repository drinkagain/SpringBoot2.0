package com.jiuxian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStream;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/test")
@ServletComponentScan("com.jiuxian")
@SpringBootApplication
public class SpringbootInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootInterceptorApplication.class, args);
    }


    @GetMapping(value = "/hello1")
    public ResponseEntity<String> hello() throws InterruptedException {
        Thread.sleep(500);
        return ResponseEntity.ok("HelloWorld");
    }

    @GetMapping(value = "/hello2")
    public StreamingResponseBody hello2() throws InterruptedException {
        Thread.sleep(500);
        return (OutputStream outputStream) -> {
            outputStream.write("HelloWorld".getBytes());
            outputStream.flush();
            outputStream.close();
        };
    }

    @GetMapping(value = "/hello3")
    public Future<String> hello3() throws InterruptedException {
        Thread.sleep(500);
        return new AsyncResult<>("HelloWorld");
    }
}

