package com.jiuxian.report.service;

import com.jiuxian.service.BaseService;
import com.jiuxian.report.entity.ReportSqlColumn;

import java.util.List;
import java.util.Map;

public interface ReportSqlColumnService extends BaseService<ReportSqlColumn> {
    Map<String, ReportSqlColumn> getColumnShow(String reportSqlId);

    List<ReportSqlColumn> getByReportSqlId(String reportSqlId);
}
