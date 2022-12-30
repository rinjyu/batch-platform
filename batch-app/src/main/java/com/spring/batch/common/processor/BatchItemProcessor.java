package com.spring.batch.common.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class BatchItemProcessor<I, O> implements ItemProcessor<I, O> {

    @Override
    public O process(I i) throws Exception {
        return null;
    }
}
