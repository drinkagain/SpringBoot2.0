package com.jiuxian.core.exception;

public class BusinessException extends RuntimeException {
    private Object[] params = new Object[0];

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Object... params) {
        super(message);
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
