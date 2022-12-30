package com.spring.batch.common.exception;

import com.spring.batch.common.handler.BatchApiErrorResponse;

public class BatchRetryException extends BatchAbstractException {

    public BatchRetryException(BatchApiErrorResponse batchApiErrorResponse) {
        super(batchApiErrorResponse);
    }
}