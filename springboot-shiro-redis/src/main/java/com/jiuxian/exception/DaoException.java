package com.jiuxian.exception;

public class DaoException extends RuntimeException {
    private Object[] params = new Object[0];

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Object... params) {
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
