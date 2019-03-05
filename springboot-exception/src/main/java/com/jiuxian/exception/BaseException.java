package com.jiuxian.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Author: LIU ZEJUN
 * Date: 2019-03-05 19:23:00
 * Comment:
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private Integer code;
    private String message;

    public BaseException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
