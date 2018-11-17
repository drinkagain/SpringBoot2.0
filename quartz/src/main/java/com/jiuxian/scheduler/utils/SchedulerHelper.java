package com.jiuxian.scheduler.utils;

import com.jiuxian.scheduler.entity.SysJob;
import com.jiuxian.scheduler.repository.SysJobRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liuzejun
 * @Date 14:30 2018/3/7
 */
@Component
@Lazy(false)
public class SchedulerHelper {
    public final static String SCHEDULER_KEY_JOBSERVICE = "JOB_SERVICE";
    private static final Log logger = LogFactory.getLog(SchedulerHelper.class);
    private static final String IDENTITY_JOB_PREFIX = "job_";
    private static final String IDENTITY_TRIGGER_PREFIX = "trigger_";

    @Resource(name = "scheduler")
    private Scheduler scheduler;
    @Autowired
    private SysJobRepository jobRepository;
    @Autowired
    private StartJobSchedulerListener startJobSchedulerListener;

    private Set<String> existJobs = new HashSet<>();

    /**
     * 根据jobEntity获得trigger
     */

    public static TriggerBuilder<CronTrigger> generateTriggerBuilder(SysJob job) {
        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(job))
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression())
                        .withMisfireHandlingInstructionDoNothing());
        if (job.getSyncBeginTime() != null)
            triggerBuilder.startAt(job.getSyncBeginTime());
        else
            triggerBuilder.startNow();

        if (job.getSyncEndTime() != null)
            triggerBuilder.endAt(job.getSyncEndTime());

        return triggerBuilder;
    }

    /**
     * 获得任务的jobKey
     */
    public static JobKey getJobKey(String jobId, String jobType) {
        return new JobKey(IDENTITY_JOB_PREFIX + jobId, IDENTITY_JOB_PREFIX + jobType);
    }

    /**
     * 获得任务的jobKey
     */

    public static JobKey getJobKey(SysJob job) {
        return getJobKey(job.getId(), job.getJobType());
    }

    /**
     * 获得trigger的triggerkey
     */
    public static TriggerKey getTriggerKey(SysJob job) {
        return getTriggerKey(job.getId(), job.getJobType(), false);
    }

    /**
     * 获得trigger的triggerkey
     *
     * @param random
     */
    private static TriggerKey getTriggerKey(String jobId, String jobType, boolean random) {
        String name = IDENTITY_TRIGGER_PREFIX + jobId;
        if (random) {
            name = name + "_" + System.currentTimeMillis();
        }
        return new TriggerKey(name, IDENTITY_TRIGGER_PREFIX + jobType);
    }

    @PostConstruct
    public void shedulerInit() {
        try {
            scheduler.getContext().put(SCHEDULER_KEY_JOBSERVICE, jobRepository);
            scheduler.getListenerManager().addSchedulerListener(startJobSchedulerListener);
            startJobSchedulerListener.setSchedulerHelper(this);
            scheduler.start();
        } catch (Exception e) {
            logger.error("shedulerInit", e);
        }
    }

    public boolean createAndStartJob(SysJob job) {
        try {
            JobDetail jobDetail = generateJobDetail(job);
            CronTrigger trigger = generateTriggerBuilder(job).build();
            scheduler.scheduleJob(jobDetail, trigger);
            job.setRuntimeNext(trigger.getNextFireTime());
            job.setJobStatus("AWAITING");
            jobRepository.save(job);
            existJobs.add(job.getId());
            return true;
        } catch (SchedulerException e) {
            logger.error("createAndStartJob", e);
            return false;
        } catch (Exception e) {
            logger.error("createAndStartJob", e);
            return false;
        }
    }

    /**
     * 清除
     */
    public void clearAllScheduler() {
        try {
            scheduler.clear();
            existJobs.clear();
        } catch (SchedulerException e) {
            logger.error("clearAllScheduler", e);
        }
    }

    /**
     * 根据jobId和类型删除
     */
    public boolean removeJob(String jobId, String jobType) {
        try {
            scheduler.deleteJob(getJobKey(jobId, jobType));
            existJobs.remove(jobId);
            return true;
        } catch (SchedulerException e) {
            logger.error("removeJob", e);
            return false;
        }
    }

    /**
     * 暂停任务并返回结果
     *
     * @param jobId
     * @param jobType
     * @return
     */
    public boolean pauseJob(String jobId, String jobType) {
        try {
            scheduler.pauseJob(getJobKey(jobId, jobType));
            return true;
        } catch (SchedulerException e) {
            logger.error("resumeJob", e);
            return false;
        }
    }

    /**
     * 恢复任务并返回下次执行时间
     *
     * @param jobId
     * @param jobType
     * @return
     */
    public Date resumeJob(String jobId, String jobType) {
        try {
            scheduler.resumeJob(getJobKey(jobId, jobType));
            Trigger trigger = scheduler.getTrigger(getTriggerKey(jobId, jobType, false));
            return trigger.getNextFireTime();
        } catch (SchedulerException e) {
            logger.error("resumeJob", e);
            return null;
        }
    }

    public void update(String jobId, String jobType) {
        SysJob job = jobRepository.getOne(jobId);
        removeJob(jobId, jobType);
        if (JobConstantUtils.JOB_ENABLE_STATUS_ENABLE.equals(job.getEnableStatus())) {
            createAndStartJob(job);
        }
    }

    /**
     * 马上只执行一次任务
     */
    public boolean executeOnceJob(String jobId, String jobType) {
        try {
            Calendar end = Calendar.getInstance();
            TriggerBuilder<SimpleTrigger> simpleTriggerBuilder = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(jobId, jobType, true)).forJob(getJobKey(jobId, jobType))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2));
            end.add(Calendar.SECOND, 2);
            simpleTriggerBuilder.startAt(end.getTime());
            end.add(Calendar.SECOND, 5);
            simpleTriggerBuilder.endAt(end.getTime());

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("jobId", jobId);
            simpleTriggerBuilder.usingJobData(jobDataMap);
            Trigger trigger = simpleTriggerBuilder.build();
            scheduler.scheduleJob(trigger);
            return true;
        } catch (SchedulerException e) {
            logger.error("executeOneceJob", e);
            return false;
        }
    }

    /**
     * 启动一些scheduler里没有的active的jobDetail
     */
    public void createActiveJobFromDB() throws SchedulerException {
        List<SysJob> jobs = jobRepository.findByEnableStatus(JobConstantUtils.JOB_ENABLE_STATUS_ENABLE);
        for (SysJob job : jobs) {
            if (scheduler.getJobDetail(getJobKey(job)) == null)
                createAndStartJob(job);
        }
    }

    public JobDetail generateJobDetail(SysJob job) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobId", job.getId());
        return JobBuilder.newJob(JobBean.class).withIdentity(getJobKey(job)).usingJobData(jobDataMap)
                .requestRecovery(true).storeDurably(true).build();
    }

    public static SysJobRepository getJobRepository(JobExecutionContext context) {
        try {
            return (SysJobRepository) context.getScheduler().getContext().get(SchedulerHelper.SCHEDULER_KEY_JOBSERVICE);
        } catch (SchedulerException e) {
            logger.error("SchedulerHelper.getJobService", e);
            return null;
        }
    }
}
