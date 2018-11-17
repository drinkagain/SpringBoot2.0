package com.jiuxian.report.common;

import java.util.Map;

public class QueryCondition {
    private String reportId;
    private Map<String, Object> queryCondition;
    private Sort sort;
    private Integer count = 20;
    private Integer currPage = 1;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Map<String, Object> getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(Map<String, Object> queryCondition) {
        this.queryCondition = queryCondition;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    private static class Sort {
        private String sort;
        private String direction;

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }

}
