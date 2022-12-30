package com.spring.batch.job.mybatisJob;

import com.spring.batch.common.enums.JobName;
import com.spring.batch.common.reader.BatchOraclelMybatisCursorItemReader;
import com.spring.batch.common.writer.BatchOracleMybatisItemWriter;
import com.spring.batch.domain.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MybatisItemWriterJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job mybatisItemWriterJob() {
        return jobBuilderFactory.get(JobName.MYBATIS_ITEM_WRITER_JOB.getJobName())
                .start(mybatisItemWriter_Step1())
                .build();
    }

    @Bean
    public Step mybatisItemWriter_Step1() {
        return stepBuilderFactory.get("mybatisItemWriter_Step1")
                .<Inventory, Inventory>chunk(1)
                .reader(oracleMybatisCursorSampleReader())
                .processor((ItemProcessor<Inventory, Inventory>) itemCuinv -> {
                    log.info("{}", itemCuinv);
                    return itemCuinv;
                })
                .writer(oracleMybatisItemBatchSampleWriter())
                .build();
    }

    @Bean
    @StepScope
    public MyBatisCursorItemReader<Inventory> oracleMybatisCursorSampleReader() {
        return new BatchOraclelMybatisCursorItemReader<Inventory>()
                .queryId("com.spring.batch.mapper.oracle.OracleMapper.getItemCuinvSample")
                .build();
    }

    @Bean
    @StepScope
    public MyBatisBatchItemWriter<Inventory> oracleMybatisItemBatchSampleWriter() {
        return new BatchOracleMybatisItemWriter<Inventory>()
                .statementId("com.spring.batch.mapper.oracle.OracleMapper.insertItemCuinv")
                .build();
    }
}
