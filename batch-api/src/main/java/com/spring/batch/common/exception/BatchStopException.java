package com.spring.batch.common.exception;

import com.spring.batch.common.handler.BatchApiErrorResponse;

public class BatchStopException extends BatchAbstractException {

    public BatchStopException(BatchApiErrorResponse batchApiErrorResponse) {
        super(batchApiErrorResponse);
    }
}
