package com.jiuxian.report.dao;

import com.jiuxian.core.dao.BaseDao;
import com.jiuxian.report.entity.ReportSql;
import com.jiuxian.report.vo.ReportSqlNameVo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportSqlDao extends BaseDao<ReportSql> {

    @Modifying
    @Query(value = "select t.id as reportSqlId,t.name as name from ReportSql t")
    List<ReportSqlNameVo> getReportSql();
}
