package com.jiuxian.report.entity;

import com.jiuxian.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 报表Sql
 */
@Entity
@Table(name = "report_sql")
@Data
public class ReportSql extends BaseEntity {
    /**
     * 报表名
     */
    @NotNull(message = "报表名 不能为空")
    private String name;
    /**
     * 原sql
     */
    @NotNull(message = "Sql语句不能为空")
    private String originalSql;
    /**
     * 通过原Sql生成的结果集sql
     */
    private String resultSql;
    /**
     * count sql
     */
    private String countSql;
    /**
     * 备注
     */
    private String comments;
}
