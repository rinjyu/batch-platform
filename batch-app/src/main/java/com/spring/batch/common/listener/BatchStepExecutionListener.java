package com.spring.batch.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        String stepName = stepExecution.getStepName();
        log.info(">>> jobName {}의 stepName {} ::: 실행 전 <<<", jobName, stepName);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        String stepName = stepExecution.getStepName();
        BatchStatus batchStatus = stepExecution.getStatus();
        ExitStatus exitStatus = stepExecution.getExitStatus();
        log.info(">>> jobName {}의 stepName {} ::: 실행 결과 <<<", jobName, stepName);
        log.info(">>> batchStatus ::: {} <<<", batchStatus);
        log.info(">>> exitStatus ::: {} <<<", exitStatus);
        switch (batchStatus) {
            case FAILED:
                log.info(">>> jobName {}의 stepName {} 수행이 실패했습니다. 담당자 확인바랍니다.", jobName, stepName);
                break;
            case COMPLETED:
                log.info(">>> jobName {}의 stepName {} 수행이 완료되었습니다.", jobName, stepName);
                break;
            default:
                log.info(">>> jobName {}의 stepName {} 수행 완료, batchStatus : {}", jobName, stepName, batchStatus);
                break;
        }
        return exitStatus;
    }
}
