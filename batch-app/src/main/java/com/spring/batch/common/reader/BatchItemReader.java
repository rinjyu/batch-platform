package com.spring.batch.common.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

@Slf4j
public class BatchItemReader<T> implements ItemReader<T> {

    @Override
    public T read() throws Exception {
        return null;
    }
}