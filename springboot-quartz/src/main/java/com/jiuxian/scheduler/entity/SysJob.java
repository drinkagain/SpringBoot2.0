package com.jiuxian.scheduler.entity;

import java.util.Date;

import javax.persistence.*;

import com.jiuxian.entity.BaseEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Lazy(false)
@Table(name = "TS_JOB")
public class SysJob extends BaseEntity {

    private String jobName;
    private String jobType;
    private String cronExpression;
    private Date runtimeLast;
    private Date runtimeNext;
    private String jobStatus;
    private String enableStatus;
    private Integer runtimes;
    private Long runDuration;
    private Date syncBeginTime;
    private Date syncEndTime;
    private String jobMemo;
    private String jobClass;
    private String jobMethod;
    private String jobObject;

    @Column(name = "JOB_NAME")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Column(name = "JOB_TYPE")
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Column(name = "CRON_EXPRESSION")
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RUNTIME_LAST")
    public Date getRuntimeLast() {
        return runtimeLast;
    }

    public void setRuntimeLast(Date runtimeLast) {
        this.runtimeLast = runtimeLast;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RUNTIME_NEXT")
    public Date getRuntimeNext() {
        return runtimeNext;
    }

    public void setRuntimeNext(Date runtimeNext) {
        this.runtimeNext = runtimeNext;
    }

    @Column(name = "JOB_STATUS")
    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Column(name = "ENABLE_STATUS")
    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Column(name = "RUN_TIMES")
    public Integer getRuntimes() {
        return runtimes;
    }

    public void setRuntimes(Integer runtimes) {
        this.runtimes = runtimes;
    }

    @Column(name = "RUN_DURATION")
    public Long getRunDuration() {
        return runDuration;
    }

    public void setRunDuration(Long runDuration) {
        this.runDuration = runDuration;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SYNC_BEGIN_TIME")
    public Date getSyncBeginTime() {
        return syncBeginTime;
    }

    public void setSyncBeginTime(Date syncBeginTime) {
        this.syncBeginTime = syncBeginTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SYNC_END_TIME")
    public Date getSyncEndTime() {
        return syncEndTime;
    }

    public void setSyncEndTime(Date syncEndTime) {
        this.syncEndTime = syncEndTime;
    }

    @Column(name = "JOB_MEMO")
    public String getJobMemo() {
        return jobMemo;
    }

    public void setJobMemo(String jobMemo) {
        this.jobMemo = jobMemo;
    }

    @Column(name = "JOB_CLASS")
    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    @Column(name = "JOB_METHOD")
    public String getJobMethod() {
        return jobMethod;
    }

    public void setJobMethod(String jobMethod) {
        this.jobMethod = jobMethod;
    }

    @Column(name = "JOB_OBJECT")
    public String getJobObject() {
        return jobObject;
    }

    public void setJobObject(String jobObject) {
        this.jobObject = jobObject;
    }

}
