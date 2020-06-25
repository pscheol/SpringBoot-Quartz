package com.example.quartzexam.schedule;

import com.example.quartzexam.DefaultJobExecute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SchedulerService {

    private final Scheduler scheduler;

    /**
     * 스케줄 생성
     * @param jobName
     * @param jobGroupName
     * @param cron
     * @param param
     * @return
     * @throws Exception
     */
    public JobKey createSchedule(String jobName, String jobGroupName, String cron, Map<String, Object> param) throws Exception {
        return createSchedule(DefaultJobExecute.class, jobName, jobGroupName, cron, param, new Date());
    }


    /**
     * 스케줄 생성
     * @param jobName
     * @param jobGroupName
     * @param cron
     * @param param
     * @return
     * @throws Exception
     */
    public JobKey createSchedule(Class<? extends Job> clz, String jobName, String jobGroupName, String cron, Date triggerStartTime , Map<String, Object> param) throws Exception {
        return createSchedule(DefaultJobExecute.class, jobName, jobGroupName, cron, param, triggerStartTime);
    }

    /**
     * 스케줄 생성
     * @param jobName
     * @param jobGroupName
     * @param cron
     * @param param
     * @return
     * @throws Exception
     */
    public JobKey createSchedule(Class<? extends Job> clz, String jobName, String jobGroupName, String cron, Map<String, Object> param, Date triggerStartTime) throws Exception {
        JobDetail jobDetail = Jobs.createJobDetail(clz, jobName, jobGroupName, param);
        CronTrigger trigger = Triggers.createTrigger(jobName, jobGroupName, triggerStartTime, cron);
        scheduler.scheduleJob(jobDetail, trigger);
        return jobDetail.getKey();
    }


    /**
     * 스케줄 일시중지
     * @param jobName
     * @param jobGroupName
     * @throws Exception
     */
    public void pauseSchedule(String jobName, String jobGroupName) throws Exception {
        pauseSchedule(JobKey.jobKey(jobName, jobGroupName));
    }

    /**
     * 스케줄 일시중지
     * @param jobKey
     * @throws Exception
     */
    public void pauseSchedule(JobKey jobKey) throws Exception {
        scheduler.pauseJob(jobKey);
    }

    /**
     * 스케줄 다시시작
     * @param jobName
     * @param jobGroupName
     * @throws Exception
     */
    public void resumeSchedule(String jobName, String jobGroupName) throws Exception {
        resumeSchedule(JobKey.jobKey(jobName, jobGroupName));
    }

    /**
     *
     * @param jobKey
     * @throws Exception
     */
    public void resumeSchedule(JobKey jobKey) throws Exception {
        scheduler.resumeJob(jobKey);
    }

    /**
     * 스케줄 종료 및 삭제
     * @param jobName
     * @param jobGroupKey
     * @return
     * @throws Exception
     */
    public boolean stopSchedule(String jobName, String jobGroupKey) throws Exception {
        return stopSchedule(JobKey.jobKey(jobName, jobGroupKey), TriggerKey.triggerKey(jobName, jobGroupKey));
    }

    /**
     * 스케줄 종료 및 삭제
     * @param jobKey
     * @param triggerKey
     * @return
     * @throws Exception
     */
    public boolean stopSchedule(JobKey jobKey, TriggerKey triggerKey) throws Exception {
        scheduler.unscheduleJob(triggerKey);
        return scheduler.deleteJob(jobKey);
    }
}
