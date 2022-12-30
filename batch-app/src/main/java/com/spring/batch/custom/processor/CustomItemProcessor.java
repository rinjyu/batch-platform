package com.spring.batch.custom.processor;

import com.spring.batch.common.processor.BatchItemProcessor;
import com.spring.batch.domain.Inventory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomItemProcessor extends BatchItemProcessor<Inventory, Inventory> {

    @Override
    public Inventory process(Inventory inventory) {
        return Inventory.builder()
                .itemId(inventory.getItemId())
                .usablInvQty(inventory.getUsablInvQty() * 2)
                .build();
    }
}