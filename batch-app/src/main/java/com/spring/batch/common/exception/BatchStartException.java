package com.spring.batch.common.exception;

public class BatchStartException extends RuntimeException {

    public BatchStartException() {
        super();
    }

    public BatchStartException(String message) {
        super(message);
    }
}
