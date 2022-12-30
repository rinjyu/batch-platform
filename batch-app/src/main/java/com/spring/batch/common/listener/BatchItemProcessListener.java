package com.spring.batch.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchItemProcessListener<T, S> implements ItemProcessListener<T, S> {

    @Override
    public void beforeProcess(T t) {

    }

    @Override
    public void afterProcess(T t, S s) {

    }

    @Override
    public void onProcessError(T t, Exception e) {
        log.error(">> onProcessError ::: {}", e.getMessage());
    }
}
