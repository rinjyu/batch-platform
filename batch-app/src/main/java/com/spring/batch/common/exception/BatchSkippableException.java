package com.spring.batch.common.exception;

public class BatchSkippableException extends Exception {

    public BatchSkippableException() {
        super();
    }

    public BatchSkippableException(String message) {
        super(message);
    }
}