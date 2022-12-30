package com.spring.batch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/batch/job")
@RequiredArgsConstructor
public class BatchJobController {

    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    @PostMapping("/start")
    public ResponseEntity<String> start(@RequestParam("jobName") String jobName) throws Exception {
        jobExplorer.findRunningJobExecutions(jobName);
        jobOperator.start(jobName, "run.date");
        return ResponseEntity.ok("batch is started.");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stop(@RequestParam("jobName") String jobName) throws Exception {
        Set<JobExecution> jobExecutions = jobExplorer.findRunningJobExecutions(jobName);
        JobExecution jobExecution = jobExecutions.iterator().next();
        jobOperator.stop(jobExecution.getJobId());
        return ResponseEntity.ok("batch is stopped.");
    }

    @PostMapping(value = "/restart")
    public ResponseEntity<String> restart(@RequestParam("jobName") String jobName) throws Exception {
        Set<JobExecution> jobExecutions = jobExplorer.findRunningJobExecutions(jobName);
        JobExecution jobExecution = jobExecutions.iterator().next();
        jobOperator.restart(jobExecution.getJobId());
        return ResponseEntity.ok("batch is restarted.");
    }

}
