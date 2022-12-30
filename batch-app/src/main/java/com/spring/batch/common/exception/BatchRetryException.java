package com.spring.batch.common.exception;

public class BatchRetryException extends Exception {

    public BatchRetryException() {
        super();
    }

    public BatchRetryException(String message) {
        super(message);
    }
}