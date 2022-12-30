package com.spring.batch.controller;

import com.spring.batch.common.exception.BatchFailedException;
import com.spring.batch.common.handler.BatchApiErrorResponse;
import com.spring.batch.domain.SimpleJob;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch/simple-job")
@RequiredArgsConstructor
public class SimpleJobExecuteController {

    private final Job job;
    private final JobLauncher jobLauncher;

    @PostMapping("/execute")
    public ResponseEntity<String> execute(@RequestBody SimpleJob simpleJob) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", simpleJob.getRequestDate())
                .addString("runner", simpleJob.getRunner())
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        String jobName = jobExecution.getJobInstance().getJobName();
        BatchStatus batchStatus = jobExecution.getStatus();
        ExitStatus exitStatus = jobExecution.getExitStatus();
        if (exitStatus != ExitStatus.COMPLETED) {
            throw new BatchFailedException(new BatchApiErrorResponse(jobName, batchStatus, exitStatus));
        }
        return ResponseEntity.ok(String.format("%s %s",
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getExitStatus()));
    }
}
