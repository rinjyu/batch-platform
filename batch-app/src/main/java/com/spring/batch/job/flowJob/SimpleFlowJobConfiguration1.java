package com.spring.batch.job.flowJob;

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
public class SimpleFlowJobConfiguration1 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleFlowJob1() {
        return jobBuilderFactory.get(JobName.SIMPLE_FLOW_JOB1.getJobName())
                .start(simpleFlowJob1_Flow())
                .next(simpleFlowJob1_Step3())
                .end()
                .build();
    }

    @Bean
    public Flow simpleFlowJob1_Flow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleFlowJob1_Flow");
        flowBuilder.start(simpleFlowJob1_Step1())
                .next(simpleFlowJob1_Step2())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Step simpleFlowJob1_Step1() {
        return stepBuilderFactory.get("simpleFlowJob1_Step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob1_Step1 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step simpleFlowJob1_Step2() {
        return stepBuilderFactory.get("simpleFlowJob1_Step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob1_Step2 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step simpleFlowJob1_Step3() {
        return stepBuilderFactory.get("simpleFlowJob1_Step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob1_Step3 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}