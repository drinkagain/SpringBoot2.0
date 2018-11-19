package com.jiuxian.report.service;

import com.jiuxian.service.BaseService;
import com.jiuxian.service.PageResult;
import com.jiuxian.report.common.ExportData;
import com.jiuxian.report.entity.ReportSql;
import com.jiuxian.report.entity.ReportSqlColumn;
import com.jiuxian.report.vo.ReportSqlNameVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ReportSqlService extends BaseService<ReportSql>, ExportData {
    @Transactional
    List<ReportSqlColumn> saveReportSqlAndColumns(ReportSql reportSql);

    @Transactional
    List<LinkedHashMap<String, Object>> getMapList(String sql);

    int getCount(String sql);

    PageResult getPagination(String uid, int currPage, int count, Map<String, Object> queryCondition, Map<String, ReportSqlColumn> reportSqlColumnMap);

    Map<String, ReportSqlColumn> getColumnShow(String reportSqlId);

    List<ReportSqlColumn> getReportSqlColumn(String reportSqlId);

    void updateSqlColumn(List<ReportSqlColumn> sqlColumns);

    List<ReportSqlColumn> getConditionSqlColumn(String reportSqlId);

    List<ReportSqlNameVo> getReportSql();
}
