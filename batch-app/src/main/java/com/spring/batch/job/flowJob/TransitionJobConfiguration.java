package com.spring.batch.job.flowJob;

import com.spring.batch.common.enums.JobName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
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
public class TransitionJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job transitionJob() {
        return this.jobBuilderFactory.get(JobName.TRANSITION_JOB.getJobName())
                .start(transitionJob_Step1())
                    .on(BatchStatus.FAILED.name())
                    .to(transitionJob_Step2())
                    .on(BatchStatus.FAILED.name())
                    .stop()
                .from(transitionJob_Step1())
                    .on("*")
                    .to(transitionJob_Step3())
                    .next(transitionJob_Step4())
                .from(transitionJob_Step2())
                    .on("*")
                    .to(transitionJob_Step5())
                .end()
                .build();
    }

    @Bean
    public Step transitionJob_Step1() {
        return stepBuilderFactory.get("transitionJob_Step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> transitionJob_Step1 <<");
                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step transitionJob_Step2() {
        return stepBuilderFactory.get("transitionJob_Step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> transitionJob_Step2 <<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step transitionJob_Step3() {
        return stepBuilderFactory.get("transitionJob_Step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> transitionJob_Step3 <<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step transitionJob_Step4() {
        return stepBuilderFactory.get("transitionJob_Step4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> transitionJob_Step4 <<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step transitionJob_Step5() {
        return stepBuilderFactory.get("transitionJob_Step5")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> transitionJob_Step5 <<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}