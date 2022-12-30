package com.spring.batch.common.adapter;

import org.springframework.batch.item.adapter.ItemReaderAdapter;

import java.util.Objects;

public class BatchItemReaderAdapter<T> extends ItemReaderAdapter<T> {

    public BatchItemReaderAdapter() {
        super();
    }

    @Override
    public T read() throws Exception {
        T item = super.read();
        if (Objects.isNull(item)) {
            return null;
        }
        return item;
    }
}
