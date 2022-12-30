package com.spring.batch.common.config;

import com.spring.batch.common.exception.BatchStartException;
import com.spring.batch.common.exception.BatchStopException;
import com.spring.batch.common.handler.BatchApiErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Set;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJobConfig {
    private final JobExplorer jobExplorer;
    private final JobOperator jobOperator;
    private final JobRepository jobRepository;
    private final JobLauncher jobLauncher;

    @Bean
    public JobLauncher asyncJobLauncher(){
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        simpleJobLauncher.setTaskExecutor(asyncTaskExecutor());
        return simpleJobLauncher;
    }

    @Bean
    public TaskExecutor asyncTaskExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setConcurrencyLimit(5);
        return simpleAsyncTaskExecutor;
    }

    @Bean
    public TaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public BatchStatus startSyncBatchJob(Job job, JobParameters jobParameters) throws BatchStartException {
        log.info(">>> {} Start <<<", job.getName());
        JobExecution jobExecution = null;
        try {
            jobExecution = jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            log.error("[startSyncBatchJob] {} error ::: {}", job.getName(), e.getMessage());
            throw new BatchStartException(new BatchApiErrorResponse(job.getName(), jobExecution.getStatus(), jobExecution.getExitStatus()));
        }
        return jobExecution.getStatus();
    }

    public BatchStatus startAsyncBatchJob(Job job, JobParameters jobParameters) throws BatchStartException {
        log.info(">>> {} Start <<<", job.getName());
        JobExecution jobExecution = null;
        try {
            jobExecution = asyncJobLauncher().run(job, jobParameters);
        } catch (Exception e) {
            log.error("[startAsyncBatchJob] {} error ::: {}", job.getName(), e.getMessage());
            throw new BatchStartException(new BatchApiErrorResponse(job.getName(), jobExecution.getStatus(), jobExecution.getExitStatus()));
        }
        return jobExecution.getStatus();
    }

    public void stopBatchJob(Job job) throws BatchStopException {
        Set<JobExecution> jobExecutions = jobExplorer.findRunningJobExecutions(job.getName());
        for (JobExecution jobExecution : jobExecutions) {
            if (jobExecution.getStatus() == BatchStatus.STARTED || jobExecution.getStatus() == BatchStatus.STARTING) {
                try {
                    jobOperator.stop(jobExecution.getId());
                } catch (Exception e) {
                    log.error("[stopBatchJob] {} error = {}", job.getName(), e.getMessage());
                    throw new BatchStopException(new BatchApiErrorResponse(job.getName(), jobExecution.getStatus(), jobExecution.getExitStatus()));
                }

                log.info(">>> {} (job Id ::: {}) stopped <<<", job.getName(), jobExecution.getId());
            }
        }
    }
}
