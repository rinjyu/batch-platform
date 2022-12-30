package com.spring.batch.common.exception;

import com.spring.batch.common.handler.BatchApiErrorResponse;

public class BatchSkippableException extends BatchAbstractException {

    public BatchSkippableException(BatchApiErrorResponse batchApiErrorResponse) {
        super(batchApiErrorResponse);
    }
}