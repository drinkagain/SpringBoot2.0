package com.jiuxian.base;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Author: LIU ZEJUN
 * Date: 2019-03-05 19:29:00
 * Comment:
 */

@Data
public class ResponseResult<T> {

    private Integer code;
    private String message;
    private T data;

    public ResponseResult(T data) {
        this.code = HttpStatus.OK.value();
        this.data = data;
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
