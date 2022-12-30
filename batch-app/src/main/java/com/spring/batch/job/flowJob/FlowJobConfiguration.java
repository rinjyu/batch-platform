package com.spring.batch.job.flowJob;

import com.spring.batch.common.enums.JobName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FlowJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJob() {
        return this.jobBuilderFactory.get(JobName.FLOW_JOB.getJobName())
                .start(flowJob_Step1())
                .on(BatchStatus.COMPLETED.name()).to(flowJob_Step2())
                .from(flowJob_Step1())
                .on(BatchStatus.FAILED.name()).to(flowJob_Step3())
                .end()
                .build();
    }

    @Bean
    public Step flowJob_Step1() {
        return stepBuilderFactory.get("flowJob_Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> flowJob_Step1 <<<");
                    throw new RuntimeException("flowJob_Step is failed.");
//                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step flowJob_Step2() {
        return stepBuilderFactory.get("flowJob_Step2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> flowJob_Step2 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step flowJob_Step3() {
        return stepBuilderFactory.get("flowJob_Step3")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> flowJob_Step3 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
