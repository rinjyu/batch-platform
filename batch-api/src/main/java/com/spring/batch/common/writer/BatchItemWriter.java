package com.spring.batch.common.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class BatchItemWriter<T> implements ItemWriter<T> {

    @Override
    public void write(List<? extends T> list) throws Exception {

    }
}