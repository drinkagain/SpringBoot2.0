package com.jiuxian.scheduler.entity;

import java.util.Date;

import javax.persistence.*;

import com.jiuxian.entity.BaseEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Lazy(false)
@Table(name = "TS_JOB_LOG")
public class SysJobLog extends BaseEntity {

    private static final long serialVersionUID = 1419813775351153342L;

    private String jobId;
    private Date exeTime;
    private String exeResult;
    private String errorMsg;

    @Column(name = "JOB_ID")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXE_TIME")
    public Date getExeTime() {
        return exeTime;
    }

    public void setExeTime(Date exeTime) {
        this.exeTime = exeTime;
    }

    @Column(name = "EXE_RESULT")
    public String getExeResult() {
        return exeResult;
    }

    public void setExeResult(String exeResult) {
        this.exeResult = exeResult;
    }

    @Column(name = "ERROR_MSG")
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}