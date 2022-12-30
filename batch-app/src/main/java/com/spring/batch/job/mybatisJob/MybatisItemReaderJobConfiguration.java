package com.spring.batch.job.mybatisJob;

import com.spring.batch.common.converter.ItemToParameterConverter;
import com.spring.batch.common.enums.JobName;
import com.spring.batch.common.reader.BatchOracleMybatisPagingItemReader;
import com.spring.batch.common.reader.BatchOraclelMybatisCursorItemReader;
import com.spring.batch.domain.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MybatisItemReaderJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job mybatisItemReaderJob() {
        return jobBuilderFactory.get(JobName.MYBATIS_ITEM_READER_JOB.getJobName())
                .start(mybatisItemReaderJob_cursorStep1())
                .next(mybatisItemReaderJob_cursorStep2())
                .build();
    }

    @Bean
    public Step mybatisItemReaderJob_cursorStep1() {
        return stepBuilderFactory.get("mybatisItemReaderJob_cursorStep1")
                .<Inventory, Inventory>chunk(1)
                .reader(oracleMybatisCursorItemReader(null, null, null))
                .writer(items -> {
                    for (Inventory inventory : items) {
                        log.info("==========");
                        log.info("{}", inventory);
                        log.info("==========");
                    }
                })
                .build();
    }

    @Bean
    public Step mybatisItemReaderJob_cursorStep2() {
        return stepBuilderFactory.get("mybatisItemReaderJob_cursorStep2")
                .<Inventory, Inventory>chunk(1)
                .reader(oracleMybatisPagingItemReader(null, null, null))
                .writer(items -> {
                    for (Inventory inventory : items) {
                        log.info("==========");
                        log.info("{}", inventory);
                        log.info("==========");
                    }
                })
                .build();
    }

    @Bean
    @StepScope
    public MyBatisCursorItemReader<Inventory> oracleMybatisCursorItemReader(@Value("#{jobParameters['itemId']}") final String itemId,
                                                                            @Value("#{jobParameters['uitemId']}") final String uitemId,
                                                                            @Value("#{jobParameters['salestrNo']}") final String salestrNo) {
        Map<String, Object> parameters = ItemToParameterConverter.mapConvert(Inventory.builder()
                .itemId(itemId)
                .uitemId(uitemId)
                .salestrNo(salestrNo)
                .build());
        return new BatchOraclelMybatisCursorItemReader<Inventory>()
                .queryId("com.spring.batch.mapper.oracle.OracleMapper.getItemCuinv")
                .parameterValues(parameters)
                .build();
    }

    @Bean
    @StepScope
    public MyBatisPagingItemReader<Inventory> oracleMybatisPagingItemReader(@Value("#{jobParameters['itemId']}") final String itemId,
                                                                            @Value("#{jobParameters['uitemId']}") final String uitemId,
                                                                            @Value("#{jobParameters['salestrNo']}") final String salestrNo) {
        Map<String, Object> parameters = ItemToParameterConverter.mapConvert(Inventory.builder()
                .itemId(itemId)
                .uitemId(uitemId)
                .salestrNo(salestrNo)
                .build());

        return new BatchOracleMybatisPagingItemReader<Inventory>()
                .queryId("com.spring.batch.mapper.oracle.OracleMapper.getItemCuinv")
                .parameterValues(parameters)
                .build();
    }
}
