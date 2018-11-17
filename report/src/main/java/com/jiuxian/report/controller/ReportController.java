package com.jiuxian.report.controller;

import com.jiuxian.core.service.PageResult;
import com.jiuxian.report.common.QueryCondition;
import com.jiuxian.report.entity.ReportSql;
import com.jiuxian.report.entity.ReportSqlColumn;
import com.jiuxian.report.service.ReportSqlColumnService;
import com.jiuxian.report.service.ReportSqlService;
import com.jiuxian.report.vo.ReportSqlColumnVo;
import com.jiuxian.report.vo.ReportSqlNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
    private final ReportSqlService reportSqlService;
    private final ReportSqlColumnService reportSqlColumnService;

    @Autowired
    public ReportController(ReportSqlService reportSqlService, ReportSqlColumnService reportSqlColumnService) {
        this.reportSqlService = reportSqlService;
        this.reportSqlColumnService = reportSqlColumnService;
    }

    /**
     * 查询报表数据
     *
     * @param queryCondition 查询条件
     * @return 列名和数据
     */
    @PostMapping(value = "/getReportSqlData", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getReportSqlData(@RequestBody QueryCondition queryCondition) {
        Map<String, ReportSqlColumn> columnShow = reportSqlService.getColumnShow(queryCondition.getReportId());
        PageResult pagination = reportSqlService.getPagination(queryCondition.getReportId(),
                queryCondition.getCurrPage(), queryCondition.getCount(), queryCondition.getQueryCondition(), columnShow);
        List<ReportSqlColumn> reportSqlColumn = reportSqlService.getReportSqlColumn(queryCondition.getReportId());

        List<ReportSqlColumnVo> columnVos = new ArrayList<>();
        reportSqlColumn.forEach(column -> columnVos.add(new ReportSqlColumnVo(column.getReportColumn(), column.getShowName())));

        Map<String, Object> map = new HashMap<>();
        map.put("columns", columnVos);
        map.put("tableData", pagination);
        return ResponseEntity.ok(map);
    }

    /**
     * 导出报表
     *
     * @param request
     * @param response
     * @param queryCondition 查询条件
     * @throws IOException
     */
    @PostMapping(value = "/doExport")
    public void doExport(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody QueryCondition queryCondition) throws IOException {
        ReportSql reportSql = reportSqlService.findById(queryCondition.getReportId());
        Map<String, ReportSqlColumn> columnShow = reportSqlService.getColumnShow(queryCondition.getReportId());
        reportSqlService.doExport(request, response, reportSql.getName(), queryCondition.getReportId(), queryCondition.getQueryCondition(), columnShow);
    }


    /**
     * 保存sql 语句
     *
     * @param reportSql 报表sql
     * @return
     */
    @PostMapping(value = "/saveReportSql", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity saveReportSql(@RequestBody ReportSql reportSql) {
        List<ReportSqlColumn> reportSqlColumns = reportSqlService.saveReportSqlAndColumns(reportSql);
        return ResponseEntity.ok(reportSqlColumns);
    }


    /**
     * 修改列属性
     *
     * @param sqlColumn 字段属性和定义别名
     * @return
     */
    @PostMapping(value = "/updateSqlColumn", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity updateSqlColumn(@RequestBody List<ReportSqlColumn> sqlColumn) {
        reportSqlColumnService.doSaveOrUpdateBatch(sqlColumn);
        return ResponseEntity.ok().build();
    }

    /**
     * 报表数据字段
     *
     * @param reportSqlId
     * @return
     */
    @PostMapping(value = "/getReportSqlColumn", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getReportSqlColumn(@RequestParam String reportSqlId) {
        List<ReportSqlColumn> reportSqlColumn = reportSqlService.getReportSqlColumn(reportSqlId);
        return ResponseEntity.ok(reportSqlColumn);
    }

    /**
     * 报表名
     *
     * @return
     */
    @PostMapping(value = "/getReportSqlName", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getReportSqlName() {
        List<ReportSqlNameVo> reportSqlNameVo = reportSqlService.getReportSql();
        return ResponseEntity.ok(reportSqlNameVo);
    }

    /**
     * 查询条件字段
     *
     * @param reportSqlId
     * @return
     */
    @PostMapping(value = "/getConditionColumn", produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getConditionColumn(@RequestBody String reportSqlId) {
        List<ReportSqlColumn> conditionSqlColumn = reportSqlService.getConditionSqlColumn(reportSqlId);
        List<ReportSqlColumnVo> columnVos = new ArrayList<>();
        conditionSqlColumn.forEach(column -> columnVos.add(new
                ReportSqlColumnVo(column.getReportColumn(), column.getShowName(), column.getDataType())));
        return ResponseEntity.ok(columnVos);
    }
}
