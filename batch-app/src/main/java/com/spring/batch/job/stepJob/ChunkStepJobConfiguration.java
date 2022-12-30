package com.spring.batch.job.stepJob;

import com.spring.batch.common.enums.JobName;
import com.spring.batch.custom.processor.CustomItemProcessor;
import com.spring.batch.custom.reader.CustomItemReader;
import com.spring.batch.custom.writer.CustomItemWriter;
import com.spring.batch.domain.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ChunkStepJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkStepJob() {
        return jobBuilderFactory.get(JobName.CHUNK_STEP_JOB.getJobName())
                .start(chunkStepJob_step())
                .build();
    }

    @Bean
    public Step chunkStepJob_step() {
        return stepBuilderFactory.get("chunkStepJob_step")
                .<Inventory, Inventory>chunk(3)
                .reader(chunkStepJob_ItemReader())
                .processor(chunkStepJob_ItemProcessor())
                .writer(chunkStepJob_ItemWriter())
                .build();
    }

    @Bean
    public ItemReader<Inventory> chunkStepJob_ItemReader() {
        Inventory inventory1 = Inventory.builder()
                .itemId("0000000000004")
                .uitemId("00000")
                .salestrNo("6005")
                .usablInvQty(100)
                .build();
        Inventory inventory2 = Inventory.builder()
                .itemId("0000000000005")
                .uitemId("00000")
                .salestrNo("6005")
                .usablInvQty(100)
                .build();
        Inventory inventory3 = Inventory.builder()
                .itemId("0000000000006")
                .uitemId("00000")
                .salestrNo("6005")
                .usablInvQty(100)
                .build();
        return new CustomItemReader(Arrays.asList(inventory1, inventory2, inventory3));
    }

    @Bean
    public ItemProcessor<Inventory, Inventory> chunkStepJob_ItemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<? super Inventory> chunkStepJob_ItemWriter() {
        return new CustomItemWriter();
    }
}
