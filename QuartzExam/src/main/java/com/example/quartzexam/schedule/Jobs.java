package com.example.quartzexam.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Map;

public class Jobs {
    /**
     * JobDetail 생성
     *
     * @param clz
     * @param jobName
     * @param jobGroupName
     * @param param
     * @return
     */
    public static JobDetail createJobDetail(Class<? extends Job> clz, String jobName, String jobGroupName, Map<String, Object> param) {
        return createJobDetail(clz, JobKey.jobKey(jobName, jobGroupName), param);
    }

    /**
     * JobDetail 생성
     *
     * @param clz
     * @param jobKey
     * @param param
     * @return
     */
    public static JobDetail createJobDetail(Class<? extends Job> clz, JobKey jobKey, Map<String, Object> param) {
        return JobBuilder.newJob(clz)
                .withIdentity(jobKey)
                .usingJobData(createJobDataMap(param))
                .build();
    }

    /**
     * JobDataMap 생성
     *
     * @param param
     * @return
     */
    public static JobDataMap createJobDataMap(Map<String, Object> param) {
        JobDataMap resultMap = new JobDataMap();
        if (param != null && !param.isEmpty())
            resultMap.putAll(param);
        return resultMap;
    }

    /**
     * JobDataMap 생성
     *
     * @return
     */
    public static JobDataMap createJobDataMap() {
        return createJobDataMap(null);
    }
}
