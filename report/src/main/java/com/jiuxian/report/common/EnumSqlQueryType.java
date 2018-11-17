package com.jiuxian.report.common;

public enum EnumSqlQueryType {
    LIKE("LIKE", "模糊查询"),
    SCOPE("SCOPE", "范围查询"),
    EQUAL("EQUAL", "等于");

    private String code;
    private String name;

    EnumSqlQueryType(String code, String name) {
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
