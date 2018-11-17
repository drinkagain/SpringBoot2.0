package com.jiuxian.scheduler.utils;

import com.jiuxian.scheduler.entity.SysJob;
import com.jiuxian.scheduler.entity.SysJobLog;
import com.jiuxian.scheduler.repository.SysJobLogRepository;
import com.jiuxian.scheduler.repository.SysJobRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * @author liuzejun
 * @Date 14:56 2018/3/7
 */
public class JobBean extends QuartzJobBean {
    protected static final Log logger = LogFactory.getLog(SchedulerHelper.class);

    private ApplicationContext ctx;
    private String jobId;
    private long beginTime;

    /**
     * Execute the actual job. The job data map will already have been
     * applied as bean property values by execute. The contract is
     * exactly the same as for the standard Quartz execute method.
     *
     * @param context
     * @see #execute
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SysJobLog jobLog = new SysJobLog();
        jobLog.setJobId(jobId);
        jobLog.setExeTime(context.getFireTime());
        SysJobRepository jobRepository = SchedulerHelper.getJobRepository(context);
        try {
            preExecute(context, jobRepository);
            executeJob(context, jobRepository);
            postExecute(context, jobRepository);
            jobLog.setExeResult("Success");
        } catch (Throwable e) {
            postExecute(context, jobRepository);
            logger.error("error", e);
            jobLog.setExeResult("Fail");
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            jobLog.setErrorMsg(writer.getBuffer().toString());
            throw new JobExecutionException(e);
        } finally {
            SysJobLogRepository sysJobLogRepository = (SysJobLogRepository) ctx.getBean("sysJobLogRepository");
            sysJobLogRepository.save(jobLog);
        }
    }

    private void postExecute(JobExecutionContext context, SysJobRepository jobRepository) {
        long endTime = System.currentTimeMillis();
        SysJob sysJob = jobRepository.getOne(jobId);
        if (null != context.getFireTime()) {
            sysJob.setRuntimeLast(context.getFireTime());
        }
        if (null != context.getNextFireTime()) {
            sysJob.setRuntimeNext(context.getNextFireTime());
        }
        int runtimes = (null == sysJob.getRuntimes()) ? 0 : sysJob.getRuntimes();
        sysJob.setRuntimes(runtimes + 1);
        sysJob.setRunDuration(endTime - beginTime);
        sysJob.setJobStatus(JobConstantUtils.JOB_RUN_STATUS_AWAITING);
        jobRepository.save(sysJob);
    }

    private void executeJob(JobExecutionContext context, SysJobRepository jobRepository) throws Exception {
        SysJob sysJob = jobRepository.getOne(jobId);
        Object obj = ctx.getBean(sysJob.getJobObject());
        Method method = obj.getClass().getMethod(sysJob.getJobMethod());
        method.invoke(obj);
    }

    private void preExecute(JobExecutionContext context, SysJobRepository jobRepository) {
        beginTime = System.currentTimeMillis();
        jobRepository.doUpdateJobStatus(jobId, JobConstantUtils.JOB_RUN_STATUS_RUNNING);
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext;
    }

}
