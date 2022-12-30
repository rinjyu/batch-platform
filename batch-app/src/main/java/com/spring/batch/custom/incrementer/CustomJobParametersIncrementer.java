package com.spring.batch.custom.incrementer;

import com.spring.batch.common.incrementer.BatchJobParametersIncrementer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

@Slf4j
public class CustomJobParametersIncrementer extends BatchJobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters jobParameters) {
        return new JobParametersBuilder()
                .addJobParameters(super.getNext(jobParameters))
                .addJobParameters(jobParameters)
                .toJobParameters();
    }
}
