package com.jiuxian.report.vo;


import org.springframework.util.StringUtils;

public class ReportSqlColumnVo {
    private String column;
    private String alias;
    private String dataType;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ReportSqlColumnVo(String column, String alias) {
        this.column = column;
        if (StringUtils.isEmpty(alias)) {
            this.alias = column;
        } else {
            this.alias = alias;
        }
    }

    public ReportSqlColumnVo(String column, String alias, String dataType) {
        this(column, alias);
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
