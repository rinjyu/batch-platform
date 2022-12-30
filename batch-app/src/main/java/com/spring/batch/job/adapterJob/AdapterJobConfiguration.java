package com.spring.batch.job.adapterJob;

import com.spring.batch.common.adapter.BatchItemReaderAdapter;
import com.spring.batch.common.adapter.BatchItemWriterAdapter;
import com.spring.batch.common.enums.JobName;
import com.spring.batch.domain.Inventory;
import com.spring.batch.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AdapterJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final InventoryService inventoryService;

    @Bean
    public Job adapterJob() {
        return jobBuilderFactory.get(JobName.ADAPTER_JOB.getJobName())
                .start(adapterJob_step())
                .build();
    }

    @Bean
    public Step adapterJob_step() {
        return stepBuilderFactory.get("adapterJob_Step")
                .<Inventory, Inventory>chunk(2)
                .reader(itemCuinvBatchItemReaderAdapter())
                .writer(itemCuinvBatchItemWriterAdapter())
                .build();
    }

    @Bean
    public BatchItemReaderAdapter<Inventory> itemCuinvBatchItemReaderAdapter() {
        BatchItemReaderAdapter<Inventory> batchItemReaderAdapter = new BatchItemReaderAdapter<>();
        batchItemReaderAdapter.setTargetObject(inventoryService);
        batchItemReaderAdapter.setTargetMethod("getOracleInventories");
        return batchItemReaderAdapter;
    }

    @Bean
    public BatchItemWriterAdapter<Inventory> itemCuinvBatchItemWriterAdapter() {
        BatchItemWriterAdapter<Inventory> batchItemWriterAdapter = new BatchItemWriterAdapter<>();
        batchItemWriterAdapter.setTargetObject(inventoryService);
        batchItemWriterAdapter.setTargetMethod("insertMysqlInventory");
        return batchItemWriterAdapter;
    }
}
