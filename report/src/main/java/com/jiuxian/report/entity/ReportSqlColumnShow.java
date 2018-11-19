package com.jiuxian.report.entity;

import com.jiuxian.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "report_sql_column_show")
@Data
public class ReportSqlColumnShow extends BaseEntity {
    private String reportSqlColumnId;
    private String value;
    private String name;
}
