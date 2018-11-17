package com.jiuxian.report.service.impl;

import com.jiuxian.core.service.impl.BaseServiceImpl;
import com.jiuxian.report.dao.ReportSqlColumnDao;
import com.jiuxian.report.entity.ReportSqlColumn;
import com.jiuxian.report.service.ReportSqlColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportSqlColumnServiceImpl extends BaseServiceImpl<ReportSqlColumn> implements ReportSqlColumnService {
    @Autowired
    public void setDao(ReportSqlColumnDao dao) {
        this.dao = dao;
    }

    @Override
    public Map<String, ReportSqlColumn> getColumnShow(String reportSqlId) {
        Map<String, ReportSqlColumn> map = new HashMap<>();
        ReportSqlColumn eg = new ReportSqlColumn();
        eg.setReportSqlId(reportSqlId);
        List<ReportSqlColumn> queryColumnBy = this.findByEg(eg);
        queryColumnBy.forEach((bean) -> map.put(bean.getReportColumn(), bean));
        return map;
    }

    public List<ReportSqlColumn> getByReportSqlId(String reportSqlId) {
        ReportSqlColumn eg = new ReportSqlColumn();
        eg.setReportSqlId(reportSqlId);
        return this.findByEg(eg);
    }

}
