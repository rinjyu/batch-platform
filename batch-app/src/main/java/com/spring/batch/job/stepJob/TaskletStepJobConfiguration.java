package com.spring.batch.job.stepJob;

import com.spring.batch.common.enums.JobName;
import com.spring.batch.custom.tasklet.CustomTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TaskletStepJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomTasklet customTasklet;

    @Bean
    public Job taskletStepJob() {
        return jobBuilderFactory.get(JobName.TASKLET_STEP_JOB.getJobName())
                .start(taskletStepJob_step())
                .build();
    }

    @Bean
    public Step taskletStepJob_step() {
        return stepBuilderFactory.get("taskletStepJob_step")
                .tasklet(customTasklet)
                .build();
    }
}
