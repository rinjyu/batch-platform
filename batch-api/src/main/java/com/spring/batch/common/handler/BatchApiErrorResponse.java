package com.spring.batch.common.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@JsonPropertyOrder({"timestamp", "batchStatus", "exitStatus", "message"})
@AllArgsConstructor
@NoArgsConstructor
public class BatchApiErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    private String jobName;

    private BatchStatus batchStatus;
    private ExitStatus exitStatus;
    private String exitDescription;

    public BatchApiErrorResponse(String jobName, BatchStatus batchStatus, ExitStatus exitStatus) {
        this.jobName = jobName;
        this.batchStatus = batchStatus;
        this.exitStatus = exitStatus;
        this.exitDescription = exitStatus.getExitDescription();
    }
}
