package com.spring.batch.custom.incrementer;

import com.spring.batch.common.incrementer.BatchJobParametersIncrementer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

@Slf4j
public class CustomJobParametersIncrementer extends BatchJobParametersIncrementer {

    private static String RUN_ID_KEY = "run.id";
    private String key = RUN_ID_KEY;

    @Override
    public JobParameters getNext(JobParameters jobParameters) {
        super.getNext(jobParameters);
        JobParameters parameters = ObjectUtils.isEmpty(jobParameters) ? new JobParameters() : jobParameters;
        long id = parameters.getLong(key, 0L) + 1;
        return new JobParametersBuilder().addLong(key, id).toJobParameters();
    }
}
