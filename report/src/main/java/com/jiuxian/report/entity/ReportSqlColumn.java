package com.jiuxian.report.entity;

import com.jiuxian.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "report_sql_column")
@Data
public class ReportSqlColumn extends BaseEntity {
    private String reportSqlId;
    /**
     * 报表列
     */
    private String reportColumn;
    /**
     * 报表列对应显示名
     */
    private String showName;

    /**
     * 数据类型
     *
     * @see com.jiuxian.report.common.EnumColumnDataType
     */
    private String dataType;
    /**
     * 是否作为查询条件
     */
    private Boolean isCondition;
    /**
     * 查询条件类型
     *
     * @see com.jiuxian.report.common.EnumSqlQueryType
     */
    private String queryType;
    /**
     * 列显示顺序
     */
    private Integer sequence;

}
