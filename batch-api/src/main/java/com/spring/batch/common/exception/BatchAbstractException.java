package com.spring.batch.common.exception;

import com.spring.batch.common.handler.BatchApiErrorResponse;
import lombok.Getter;

@Getter
public class BatchAbstractException extends Exception {

    private BatchApiErrorResponse batchApiErrorResponse;

    public BatchAbstractException(BatchApiErrorResponse batchApiErrorResponse) {
        super();
        this.batchApiErrorResponse = batchApiErrorResponse;
    }
}
