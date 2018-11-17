package com.jiuxian.report.common;

import com.jiuxian.bean.BeanUtil;
import com.jiuxian.core.service.PageResult;
import com.jiuxian.poi.PoiUtil;
import com.jiuxian.report.entity.ReportSqlColumn;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface ExportData {
    PageResult getPagination(String uid,
                             int currPage,
                             int count,
                             Map<String, Object> queryCondition,
                             Map<String, ReportSqlColumn> reportSqlColumnMap);

    void exportData(Sheet sheet, List<Map<String, Object>> mapList,
                    Map<String, ReportSqlColumn> reportSqlColumnMap, int rowIndex);

    default void doExport(HttpServletRequest request, HttpServletResponse response, String name,
                          String uid, Map<String, Object> queryCondition, Map<String, ReportSqlColumn> reportSqlColumnMap) throws IOException {
        int every = 500;
        int sheetIndex = 0;
        int rowIndex = 0;
        Workbook wb = new SXSSFWorkbook(every);
        Sheet sheet = wb.createSheet(String.valueOf(sheetIndex));
        PageResult pagination = this.getPagination(uid, 1, every, queryCondition, reportSqlColumnMap);
        List<Map<String, Object>> data = BeanUtil.cast(pagination.getData());
        if (data.size() > 0) {
            Map<String, Object> stringObjectMap = data.get(0);
            AtomicInteger integer = new AtomicInteger();
            stringObjectMap.forEach((key, value) -> {
                Cell cell = PoiUtil.getCell(sheet, 0, integer.getAndIncrement());
                cell.setCellValue(reportSqlColumnMap.get(key).getShowName());
            });
            this.exportData(sheet, data, reportSqlColumnMap, ++rowIndex);
            rowIndex += data.size();
            int totalPage = pagination.getPages();
            for (int i = 2; i <= totalPage; i++) {
                pagination = this.getPagination(uid, i, every, queryCondition, reportSqlColumnMap);
                data = BeanUtil.cast(pagination.getData());
                this.exportData(sheet, data, reportSqlColumnMap, ++rowIndex);
                rowIndex += data.size();
            }
            PoiUtil.setContent(request, response, wb, name);
        }
    }
}
