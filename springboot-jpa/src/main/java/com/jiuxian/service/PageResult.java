package com.jiuxian.service;

import java.util.List;

public class PageResult {

    private int records;
    private int pages;
    private int currPage;
    private List<?> data;
    private int pageSize;

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static int getStart(int currPage, int pageSize, int records) {
        int start = (currPage - 1) * pageSize;
        if (start <= 0) start = 0;
        return start;
    }

    public PageResult(int records, int pageSize, int currPage, List<?> data) {
        this.records = records;
        this.pages = this.records / pageSize;
        if (this.records % pageSize > 0) this.pages += 1;
        this.currPage = currPage;
        this.data = data;
        this.pageSize = pageSize;
    }
}