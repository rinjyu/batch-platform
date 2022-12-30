package com.spring.batch.job.simpleJob;

import com.spring.batch.common.enums.JobName;
import com.spring.batch.custom.tasklet.CustomJobParameterTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration4 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomJobParameterTasklet customJobParameterTasklet;

    @Bean
    public Job simpleJob4() {
        return jobBuilderFactory.get(JobName.SIMPLE_JOB4.getJobName())
                .start(simpleJob4_Step1(null))
                .next(simpleJob4_Step2(null))
                .build();
    }

    @Bean
    @JobScope
    public Step simpleJob4_Step1(@Value("#{jobParameters['message']}") String message) {
        return stepBuilderFactory.get("simpleJob4_Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> simpleJob4_Step1 <<<");
                    customJobParameterTasklet.message(message);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @JobScope
    public Step simpleJob4_Step2(@Value("#{jobParameters['message']}") String message) {
        return stepBuilderFactory.get("simpleJob4_Step2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> simpleJob4_Step2 <<<");
                    customJobParameterTasklet.message(message);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
