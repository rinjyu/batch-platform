package com.spring.batch.scheduler;

import com.spring.batch.common.config.BatchJobConfig;
import com.spring.batch.common.exception.BatchStartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class SimpleJobScheduler {

    private final Job job;

    private final BatchJobConfig batchJobConfig;

    public SimpleJobScheduler(@Qualifier("simpleJob1") Job job, BatchJobConfig batchJobConfig) {
        this.job = job;
        this.batchJobConfig = batchJobConfig;
    }

    @Scheduled(cron = "0 5 20 * * *")
    public void cronJobExecute() throws BatchStartException {
        log.info(">>> SimpleJobScheduler cronJobExecute <<<");
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", today)
                .toJobParameters();
        batchJobConfig.startSyncBatchJob(job, jobParameters);
    }
}