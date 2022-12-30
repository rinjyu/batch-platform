package com.spring.batch.custom.writer;

import com.spring.batch.common.writer.BatchItemWriter;
import com.spring.batch.domain.Inventory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CustomItemWriter extends BatchItemWriter<Inventory> {

    @Override
    public void write(List<? extends Inventory> list) {
        list.forEach(inventory -> log.info("{}", inventory));
    }
}
