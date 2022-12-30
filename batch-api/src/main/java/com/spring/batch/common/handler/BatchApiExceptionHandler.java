package com.spring.batch.common.handler;

import com.spring.batch.common.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BatchApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            BatchNoSkipException.class,
            BatchRetryException.class,
            BatchSkippableException.class,
            BatchStartException.class,
            BatchStopException.class
    })
    public ResponseEntity handleInternalServerErrorException(final BatchAbstractException e) {
        log.error(String.format("Exception is occurred. ::: %s", e.getMessage()));
        return ResponseEntity.of(Optional.of(e.getBatchApiErrorResponse()));
    }
}
