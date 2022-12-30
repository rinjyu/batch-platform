package com.spring.batch.common.exception;

public class BatchStopException extends RuntimeException {

    public BatchStopException() {
        super();
    }

    public BatchStopException(String message) {
        super(message);
    }
}
