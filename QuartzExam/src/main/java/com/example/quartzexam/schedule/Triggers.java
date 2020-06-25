package com.example.quartzexam.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import java.util.Date;

public class Triggers {

    /**
     * Trigger 생성
     *
     * @param triggerKey
     * @param triggerStartTime
     * @param cron
     * @return
     */
    public static CronTrigger createTrigger(TriggerKey triggerKey, Date triggerStartTime, String cron) {
        return createTrigger(triggerKey, triggerStartTime, cronSchedule(cron));
    }

    /**
     * Trigger 생성
     *
     * @param triggerKey
     * @param cron
     * @return
     */
    public static CronTrigger createTrigger(TriggerKey triggerKey, String cron) {
        return createTrigger(triggerKey, new Date(), cronSchedule(cron));
    }

    /**
     * Trigger 생성
     *
     * @param jobName
     * @param jobGroupName
     * @param cron
     * @return
     */
    public static CronTrigger createTrigger(String jobName, String jobGroupName, String cron) {
        return createTrigger(TriggerKey.triggerKey(jobName, jobGroupName), new Date(), cronSchedule(cron));
    }

    /**
     * @param jobName
     * @param jobGroupName
     * @param triggerStartTime
     * @param cron
     * @return
     */
    public static CronTrigger createTrigger(String jobName, String jobGroupName, Date triggerStartTime, String cron) {
        return createTrigger(TriggerKey.triggerKey(jobName, jobGroupName), triggerStartTime, cronSchedule(cron));
    }

    /**
     * Trigger 생성
     *
     * @param triggerKey
     * @param triggerStartTime
     * @param cronSchedule
     * @return
     */
    public static CronTrigger createTrigger(TriggerKey triggerKey, Date triggerStartTime, CronScheduleBuilder cronSchedule) {
        return TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .startAt(triggerStartTime)
                .withSchedule(cronSchedule)
                .build();
    }
    /**
     * Schedule cron 설정
     *
     * @param cron
     * @return
     */
    public static CronScheduleBuilder cronSchedule(String cron) {
        return CronScheduleBuilder.cronSchedule(cron);
    }
}
