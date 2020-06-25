package com.example.quartzexam;

import com.example.quartzexam.schedule.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuartzTest {


    private final SchedulerService schedulerService;

    @PostConstruct
    public void scheduleTest() throws Exception {
        Map<String, Object> param = new HashMap<>();
        JobKey jobKey = schedulerService.createSchedule("test", "testGroup", "0/1 * * * * ?", param);
        log.info("스케줄러 시작");
        new Thread(() -> {
            try {
                //10초후 중지
                Thread.sleep(5000);

                //스케줄 중지
                schedulerService.pauseSchedule(jobKey);
                log.info("스케줄러 일시중지");
                //10 초 후 다시시작
                Thread.sleep(10000);
                schedulerService.resumeSchedule(jobKey);
                log.info("스케줄러 다시시작");

                //10초후 스케줄러 삭제
                Thread.sleep(10000);
                schedulerService.stopSchedule("test", "testGroup");
                log.info("스케줄러 중지");

                Thread.sleep(10000);
                log.info("쿼츠 종료");


            } catch (Exception e) {

            }
        }).start();

    }
}
