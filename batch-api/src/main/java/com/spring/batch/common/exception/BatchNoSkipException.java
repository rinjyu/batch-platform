package com.spring.batch.common.exception;

import com.spring.batch.common.handler.BatchApiErrorResponse;

public class BatchNoSkipException extends BatchAbstractException {

    public BatchNoSkipException(BatchApiErrorResponse batchApiErrorResponse) {
        super(batchApiErrorResponse);
    }
}
