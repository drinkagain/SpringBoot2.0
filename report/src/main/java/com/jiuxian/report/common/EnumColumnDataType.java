package com.jiuxian.report.common;

public enum EnumColumnDataType {
    DATE("DATE", "日期类型"),
    STRING("STRING", "字符串类型"),
    INT("INT", "整形");

    private String code;
    private String name;

    EnumColumnDataType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
