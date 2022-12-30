package com.spring.batch.custom.listener;

import com.spring.batch.common.listener.BatchJobExecutionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomJobExecutionListener extends BatchJobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        super.beforeJob(jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        super.afterJob(jobExecution);
    }
}