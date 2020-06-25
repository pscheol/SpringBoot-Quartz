package com.example.quartzexam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DefaultJobExecute extends QuartzJobBean implements InterruptableJob {


    private final HelloRepository helloRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Quartz Sample Job execute test");

        Hello hello = new Hello();
        hello.setName("test");
        helloRepository.save(hello);
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.info("Interrupted Job...........");
    }
}
