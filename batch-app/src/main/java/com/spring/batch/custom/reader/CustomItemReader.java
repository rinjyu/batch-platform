package com.spring.batch.custom.reader;

import com.spring.batch.common.reader.BatchItemReader;
import com.spring.batch.domain.Inventory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomItemReader extends BatchItemReader<Inventory> {

    private List<Inventory> list;

    public CustomItemReader(List<Inventory> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public Inventory read() {
        return list.isEmpty() ? null : list.remove(0);
    }
}