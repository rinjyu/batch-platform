package com.spring.batch.common.incrementer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class BatchJobParametersIncrementer implements JobParametersIncrementer {

    private static final String batchRunDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));

    @Override
    public JobParameters getNext(JobParameters jobParameters) {
        log.info("batchRunDate ::: {}", batchRunDate);
        return new JobParametersBuilder().addString("batchRunDate", batchRunDate).toJobParameters();
    }
}