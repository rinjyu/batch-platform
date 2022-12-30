package com.spring.batch.job.simpleJob;

import com.spring.batch.common.enums.JobName;
import com.spring.batch.custom.listener.CustomJobExecutionListener;
import com.spring.batch.custom.validator.CustomJobParametersValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob2() {
        return jobBuilderFactory.get(JobName.SIMPLE_JOB2.getJobName())
                .start(simpleJob2_Step1())
                .next(simpleJob2_Step2())
                .next(simpleJob2_Step3())
                .preventRestart()
                .validator(new CustomJobParametersValidator())
                .listener(new CustomJobExecutionListener())
                .build();
    }

    @Bean
    public Step simpleJob2_Step1() {
        return stepBuilderFactory.get("simpleJob2_Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> simpleJob2_Step1 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step simpleJob2_Step2() {
        return stepBuilderFactory.get("simpleJob2_Step2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> simpleJob2_Step2 <<<");
                    log.info(">> JobParameters <<");
                    JobParameters jobParameters = stepContribution.getStepExecution().getJobParameters();
                    String requestDate = jobParameters.getString("requestDate");
                    String runner = jobParameters.getString("runner");
                    log.info(">> requestDate = {}", requestDate);
                    log.info(">> runner = {}", runner);

                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step simpleJob2_Step3() {
        return stepBuilderFactory.get("simpleJob2_Step3")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> simpleJob2_Step3 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
