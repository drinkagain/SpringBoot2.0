package com.jiuxian.scheduler.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.quartz.listeners.SchedulerListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author liuzejun
 * @Date 14:41 2018/3/7
 */
@Component
public class StartJobSchedulerListener extends SchedulerListenerSupport {
    protected final Log logger = LogFactory.getLog(StartJobSchedulerListener.class);
    private SchedulerHelper schedulerHelper;

    @Override
    public void schedulerStarted() {
        try {
            schedulerHelper.createActiveJobFromDB();
        } catch (SchedulerException e) {
            logger.error("createActiveJobFromDB", e);
        }
    }

    public void setSchedulerHelper(SchedulerHelper schedulerHelper) {
        this.schedulerHelper = schedulerHelper;
    }
}
