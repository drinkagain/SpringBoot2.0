package com.jiuxian.report.service.impl;

import com.jiuxian.core.service.impl.BaseServiceImpl;
import com.jiuxian.report.dao.ReportSqlColumnShowDao;
import com.jiuxian.report.entity.ReportSqlColumnShow;
import com.jiuxian.report.service.ReportSqlColumnShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportSqlColumnShowServiceImpl extends BaseServiceImpl<ReportSqlColumnShow>
        implements ReportSqlColumnShowService {

    @Autowired
    public void setDao(ReportSqlColumnShowDao dao) {
        this.dao = dao;
    }
}
