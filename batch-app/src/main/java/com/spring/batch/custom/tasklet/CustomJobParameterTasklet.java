package com.spring.batch.custom.tasklet;

import com.spring.batch.common.tasklet.BatchTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@StepScope
@Component
@RequiredArgsConstructor
public class CustomJobParameterTasklet extends BatchTasklet {

    @Value("#{jobParameters[message]}")
    private String message;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        super.execute(stepContribution, chunkContext);
        return RepeatStatus.FINISHED;
    }

    public void message(String message) {
        log.info("message = {}", message);
    }
}

