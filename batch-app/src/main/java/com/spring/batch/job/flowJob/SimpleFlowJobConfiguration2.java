package com.spring.batch.job.flowJob;

import com.spring.batch.common.enums.JobName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
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
public class SimpleFlowJobConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleFlowJob2() {
        return jobBuilderFactory.get(JobName.SIMPLE_FLOW_JOB2.getJobName())
                .start(simpleFlowJob2_Flow1())
                .on(BatchStatus.COMPLETED.name())
                .to(simpleFlowJob2_Flow2())
                .from(simpleFlowJob2_Flow1())
                .on(BatchStatus.FAILED.name())
                .to(simpleFlowJob2_Flow3())
                .end()
                .build();
    }

    @Bean
    public Flow simpleFlowJob2_Flow1() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleFlowJob2_Flow1");
        flowBuilder.start(simpleFlowJob2_Step1())
                .next(simpleFlowJob2_Step2())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Flow simpleFlowJob2_Flow2() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleFlowJob2_Flow2");
        flowBuilder.start(simpleFlowJob2_Flow3())
                .next(simpleFlowJob2_Step3())
                .next(simpleFlowJob2_Step4())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Flow simpleFlowJob2_Flow3() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("simpleFlowJob2_Flow3");
        flowBuilder.start(simpleFlowJob2_Step5())
                .next(simpleFlowJob2_Step6())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Step simpleFlowJob2_Step1() {
        return stepBuilderFactory.get("simpleFlowJob2_Step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob2_Step1 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step simpleFlowJob2_Step2() {
        return stepBuilderFactory.get("simpleFlowJob2_Step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob2_Step2 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step simpleFlowJob2_Step3() {
        return stepBuilderFactory.get("simpleFlowJob2_Step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob2_Step3 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step simpleFlowJob2_Step4() {
        return stepBuilderFactory.get("simpleFlowJob2_Step4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob2_Step4 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step simpleFlowJob2_Step5() {
        return stepBuilderFactory.get("simpleFlowJob2_Step5")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob2_Step5 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step simpleFlowJob2_Step6() {
        return stepBuilderFactory.get("simpleFlowJob2_Step6")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> simpleFlowJob2_Step6 <<");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
