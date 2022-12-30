package com.spring.batch.job.simpleJob;

import com.spring.batch.common.enums.JobName;
import com.spring.batch.custom.incrementer.CustomJobParametersIncrementer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration3 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob3() {
        return jobBuilderFactory.get(JobName.SIMPLE_JOB3.getJobName())
                .start(simpleJob3_Step1(null))
                .incrementer(new CustomJobParametersIncrementer())
                .build();
    }

    @Bean
    @JobScope
    public Step simpleJob3_Step1(@Value("#{jobParameters['requestDate']}") String requestDate) {
        log.info(">> simpleJob3_Step1 ::: requestDate = {}", requestDate);
        return stepBuilderFactory.get("simpleJob3_Step1")
                .tasklet(simpleJob3_Tasklet1(null))
                .startLimit(5)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet simpleJob3_Tasklet1(@Value("#{jobParameters['userId']}") String userId){
        return (stepContribution, chunkContext) -> {
            log.info(">> simpleJob3_Tasklet1 ::: userId = {}", userId);
            return RepeatStatus.FINISHED;
        };
    }
}
