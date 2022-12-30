package com.spring.batch.job.stepJob;

import com.spring.batch.common.enums.JobName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FlowStepJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowStepJob() {
        return jobBuilderFactory.get(JobName.FLOW_STEP_JOB.getJobName())
                .start(flowStepJob_FlowStep())
                .next(flowStepJob_Step2())
                .build();
    }

    public Step flowStepJob_FlowStep() {
        return stepBuilderFactory.get("flowStepJob_FlowStep")
                .flow(flowStepJob_Flow())
                .build();
    }

    @Bean
    public Flow flowStepJob_Flow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowStepJob_Flow");
        flowBuilder.start(flowStepJob_Step1())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Step flowStepJob_Step1() {
        return stepBuilderFactory.get("flowStepJob_Step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> flowStepJob_Step1 <<");
//                    throw new RuntimeException("flowStepJob_Step1 was failed");
                        return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step flowStepJob_Step2() {
        return stepBuilderFactory.get("flowStepJob_Step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> flowStepJob_Step2 <<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
