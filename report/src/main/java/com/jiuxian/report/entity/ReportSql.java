package com.jiuxian.report.entity;

import com.jiuxian.entity.BaseEntity;
import lombok.Data;

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
