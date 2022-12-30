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
public class MultipleFlowJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job multipleFlowJob() {
        return this.jobBuilderFactory.get(JobName.MULTIPLE_FLOW_JOB.getJobName())
                .start(multipleFlowJob_Flow1())
                .next(multipleFlowJob_Step3())
                .next(multipleFlowJob_Flow2())
                .next(multipleFlowJob_Step6())
                .end()
                .build();

    }

    @Bean
    public Flow multipleFlowJob_Flow1() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("multipleFlowJob_Flow1");
        flowBuilder.start(multipleFlowJob_Step1())
                .next(multipleFlowJob_Step2())
                .end();
        return flowBuilder.build();

    }

    @Bean
    public Flow multipleFlowJob_Flow2() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("multipleFlowJob_Flow2");
        flowBuilder.start(multipleFlowJob_Step4())
                .next(multipleFlowJob_Step5())
                .end();
        return flowBuilder.build();

    }

    @Bean
    public Step multipleFlowJob_Step1() {
        return stepBuilderFactory.get("multipleFlowJob_Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> multipleFlowJob_Step1 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step multipleFlowJob_Step2() {
        return stepBuilderFactory.get("multipleFlowJob_Step2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> multipleFlowJob_Step2 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step multipleFlowJob_Step3() {
        return stepBuilderFactory.get("multipleFlowJob_Step3")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> multipleFlowJob_Step3 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step multipleFlowJob_Step4() {
        return stepBuilderFactory.get("multipleFlowJob_Step4")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> multipleFlowJob_Step4 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step multipleFlowJob_Step5() {
        return stepBuilderFactory.get("multipleFlowJob_Step5")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> multipleFlowJob_Step5 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step multipleFlowJob_Step6() {
        return stepBuilderFactory.get("multipleFlowJob_Step6")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>> multipleFlowJob_Step6 <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}