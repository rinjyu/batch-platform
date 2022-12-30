package com.spring.batch.common.exception;

import com.spring.batch.common.handler.BatchApiErrorResponse;

public class BatchStartException extends BatchAbstractException {

    public BatchStartException(BatchApiErrorResponse batchApiErrorResponse) {
        super(batchApiErrorResponse);
    }
}
