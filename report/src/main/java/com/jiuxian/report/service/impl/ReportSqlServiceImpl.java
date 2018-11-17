package com.jiuxian.report.service.impl;

import com.jiuxian.bean.BeanUtil;
import com.jiuxian.core.service.PageResult;
import com.jiuxian.core.service.impl.BaseServiceImpl;
import com.jiuxian.poi.PoiUtil;
import com.jiuxian.report.common.ExportData;
import com.jiuxian.report.dao.ReportSqlDao;
import com.jiuxian.report.entity.ReportSql;
import com.jiuxian.report.entity.ReportSqlColumn;
import com.jiuxian.report.service.ReportSqlColumnService;
import com.jiuxian.report.service.ReportSqlService;
import com.jiuxian.report.common.EnumColumnDataType;
import com.jiuxian.report.common.EnumSqlQueryType;
import com.jiuxian.report.vo.ReportSqlNameVo;
import org.apache.poi.ss.usermodel.Sheet;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ReportSqlServiceImpl extends BaseServiceImpl<ReportSql> implements ReportSqlService, ExportData {
    private final ReportSqlColumnService reportSqlColumnService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ReportSqlServiceImpl(ReportSqlColumnService reportSqlColumnService) {
        this.reportSqlColumnService = reportSqlColumnService;
    }

    @Autowired
    public void setDao(ReportSqlDao dao) {
        this.dao = dao;
    }

    private ReportSqlDao getDao() {
        return ((ReportSqlDao) dao);
    }


    public List<LinkedHashMap<String, Object>> getMapList(String sql) {
        Session session = entityManager.unwrap(Session.class);
        NativeQuery nativeQuery = session.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return BeanUtil.cast(nativeQuery.getResultList());
    }

    @Override
    public int getCount(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    @Override
    public List<ReportSqlColumn> saveReportSqlAndColumns(ReportSql reportSql) {
        reportSql.setOriginalSql(reportSql.getOriginalSql());
        List<String> columns = getColumns(reportSql.getOriginalSql());
        reportSql.setResultSql(getResultSql(reportSql.getOriginalSql(), columns));
        reportSql.setCountSql(getCountSql(reportSql.getOriginalSql()));
        this.doSave(reportSql);

        List<ReportSqlColumn> reportSqlColumns = getReportSqlColumns(reportSql, columns);
        checkAndSaveColumnData(reportSql.getResultSql(), reportSqlColumns);
        return reportSqlColumns;
    }

    private List<ReportSqlColumn> getReportSqlColumns(ReportSql reportSql, List<String> columns) {
        List<ReportSqlColumn> reportSqlColumns = new ArrayList<>();
        AtomicInteger row = new AtomicInteger(0);
        columns.forEach(column -> {
            ReportSqlColumn sqlColumn = new ReportSqlColumn();
            sqlColumn.setReportColumn(column);
            sqlColumn.setReportSqlId(reportSql.getId());
            sqlColumn.setSequence(row.incrementAndGet());
            reportSqlColumns.add(sqlColumn);
        });
        return reportSqlColumns;
    }


    private void checkAndSaveColumnData(String sql, List<ReportSqlColumn> reportSqlColumns) {
        try {
            List<LinkedHashMap<String, Object>> mapList = this.getMapList(sql + " limit  0,1");
            if (CollectionUtils.isEmpty(mapList)) throw new RuntimeException("查询无数据");
            Map<String, String> dataTypeMap = new HashMap<>();

            Map<String, Object> map = mapList.get(0);
            map.forEach((key, value) -> dataTypeMap.put(key, getDataType(value).getCode()));

            reportSqlColumns.forEach(column -> {
                column.setDataType(dataTypeMap.get(column.getReportColumn()));
                column.setIsCondition(false);
                reportSqlColumnService.doSave(column);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Sql 不正确");
        }
    }

    private EnumColumnDataType getDataType(Object value) {
        if (value instanceof Date) {
            return EnumColumnDataType.DATE;
        } else if (value instanceof Integer || value instanceof Byte || value instanceof BigInteger) {
            return EnumColumnDataType.INT;
        } else {
            return EnumColumnDataType.STRING;
        }
    }

    @Override
    public Map<String, ReportSqlColumn> getColumnShow(String reportSqlId) {
        List<ReportSqlColumn> reportSqlColumns = reportSqlColumnService.getByReportSqlId(reportSqlId);
        return reportSqlColumns.stream().collect(Collectors.toMap(ReportSqlColumn::getReportColumn, value -> value));
    }

    @Override
    public List<ReportSqlColumn> getReportSqlColumn(String reportSqlId) {
        return reportSqlColumnService.getByReportSqlId(reportSqlId);
    }


    @Override
    public void updateSqlColumn(List<ReportSqlColumn> sqlColumns) {
        reportSqlColumnService.doSaveOrUpdateBatch(sqlColumns);
    }

    @Override
    public List<ReportSqlColumn> getConditionSqlColumn(String reportSqlId) {
        List<ReportSqlColumn> conditionSqlColumn = reportSqlColumnService.getByReportSqlId(reportSqlId);

        List<ReportSqlColumn> scopeColumn = conditionSqlColumn.stream().filter(column ->
                EnumSqlQueryType.SCOPE.getCode().equals(column.getQueryType())).collect(Collectors.toList());

        List<ReportSqlColumn> notScopeColumn = conditionSqlColumn.stream().filter(column ->
                !EnumSqlQueryType.SCOPE.getCode().equals(column.getQueryType())).collect(Collectors.toList());

        scopeColumn.forEach(column -> {
            column.setReportColumn(column.getReportColumn() + "Start");
            notScopeColumn.add(column);
            column.setReportColumn(column.getReportColumn() + "End");
            notScopeColumn.add(column);
        });
        notScopeColumn.addAll(scopeColumn);
        return notScopeColumn;
    }


    @Override
    public List<ReportSqlNameVo> getReportSql() {
        return getDao().getReportSql();
    }

    private Object getValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value;
    }

    private String getWhereCondition(Map<String, ReportSqlColumn> columnShow, Map<String, Object> queryCondition) {
        StringBuffer where = new StringBuffer(" where 1=1 ");
        queryCondition.forEach((key, value) -> {
            where.append(" and tab.");
            ReportSqlColumn sqlColumn = columnShow.get(key);
            if (EnumSqlQueryType.LIKE.getCode().equals(sqlColumn.getQueryType())) {
                where.append(sqlColumn.getReportColumn());
                where.append(" like ").append("'%").append(value).append("%'");
            } else if (EnumSqlQueryType.SCOPE.getCode().equals(sqlColumn.getQueryType())) {
                if (key.contains("Start")) {
                    String column = key.substring(0, key.lastIndexOf("Start"));
                    where.append(column).append(" >= ");
                } else if (key.contains("End")) {
                    String column = key.substring(0, key.lastIndexOf("End"));
                    where.append(column).append(" <= ");
                }
                where.append(getValue(value));
            } else {
                where.append(sqlColumn.getReportColumn()).append("=");
                where.append(getValue(value));
            }
        });
        return where.toString();
    }

    @Override
    public PageResult getPagination(String uid, int currPage, int count,
                                    Map<String, Object> queryCondition,
                                    Map<String, ReportSqlColumn> columnShow) {
        String whereCondition = getWhereCondition(columnShow, queryCondition);

        ReportSql reportSql = this.findById(uid);
        int records = this.getCount(reportSql.getCountSql() + whereCondition);
        int start = PageResult.getStart(currPage, count, records);
        String limit = " limit " + start + "," + count;

        List<LinkedHashMap<String, Object>> mapList = this.getMapList(reportSql.getResultSql() + whereCondition + limit);
        return new PageResult(records, count, currPage, mapList);
    }

    @Override
    public void exportData(Sheet sheet, List<Map<String, Object>> mapList, Map<String, ReportSqlColumn> reportSqlColumnMap, int rowIndex) {
        AtomicInteger row = new AtomicInteger(rowIndex);
        mapList.forEach(list -> {
            AtomicInteger cellIndex = new AtomicInteger(0);
            list.forEach((key, value) -> PoiUtil.getCell(sheet, row.get(), cellIndex.getAndIncrement()).setCellValue(getString(value)));
            row.incrementAndGet();
        });
    }

    private String getString(Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value);
    }

    private String getResultSql(String originSql, List<String> columns) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        columns.forEach(column -> sb.append("tab.").append(column).append(","));
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" FROM (").append(originSql).append(" )tab");
        return sb.toString();
    }

    private String getCountSql(String originSql) {
        return "SELECT count(1) FROM (" + originSql + " )tab";
    }

    private List<String> getColumns(String originSql) {
        int fromIndex = originSql.indexOf("FROM");
        if (fromIndex == -1) {
            fromIndex = originSql.indexOf("from");
        }
        String queryColumn = originSql.substring(6, fromIndex).trim();
        String[] split = queryColumn.split(",");
        List<String> list = new ArrayList<>();
        int start = 0;
        int length = split.length;
        while (start < length) {
            start = addColumns(split, list, start);
        }
        List<String> columns = new ArrayList<>();
        list.forEach(column -> {
            if (column.contains(" AS ")) {
                columns.add(column.split("AS")[1].trim());
            } else if (column.contains(" aS ")) {
                columns.add(column.split("aS")[1].trim());
            } else if (column.contains(" as ")) {
                columns.add(column.split("as")[1].trim());
            } else if (column.contains(" As ")) {
                columns.add(column.split("As")[1].trim());
            } else {
                columns.add(column.split(" ")[1].trim());
            }
        });
        return columns;
    }

    private int addColumns(String[] column, List<String> list, int start) {
        int length = column.length;
        int indexStart = -1;
        int indexEnd = length;
        for (; start < length; start++) {
            String s = column[start].trim();
            if (s.contains("(")) {
                indexStart = start;
            } else if (s.contains(")")) {
                indexEnd = start;
                break;
            }
            if (indexStart == -1) {
                list.add(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (indexStart != -1) {
            for (; indexStart <= indexEnd; indexStart++) {
                sb.append(column[indexStart]).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            list.add(sb.toString());
        }
        return indexEnd + 1;
    }
}
