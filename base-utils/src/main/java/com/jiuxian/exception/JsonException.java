package com.jiuxian.exception;

public class JsonException extends RuntimeException {
    private String message;

    public JsonException(String message) {
        this.message = message;
    }
}
