package com.spring.batch.common.exception;

public class BatchNoSkipException extends Exception {

    public BatchNoSkipException() {
        super();
    }

    public BatchNoSkipException(String message) {
        super(message);
    }
}
