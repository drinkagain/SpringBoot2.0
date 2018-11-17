package com.jiuxian.report.entity;

import com.jiuxian.core.entity.AuditEntity;
import com.jiuxian.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 大数据报表导出时做异步导出，先暂存，再导出
 */
@Entity
@Table(name = "report")
@Data
public class Report extends AuditEntity {
    /**
     * 报表名
     */
    private String name;
    /**
     * 报表路径
     */
    private String path;
    /**
     * 报表状态
     */
    private Byte status;
    /**
     * 下载时间
     */
    private Date downTime;
}
