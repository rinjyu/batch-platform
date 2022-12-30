package com.spring.batch.custom.tasklet;

import com.spring.batch.common.tasklet.BatchTasklet;
import com.spring.batch.domain.Inventory;
import com.spring.batch.repository.MysqlRepository;
import com.spring.batch.repository.OracleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomTasklet extends BatchTasklet {

    private final MysqlRepository mysqlRepository;
    private final OracleRepository oracleRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        super.execute(stepContribution, chunkContext);

        Inventory parameter = Inventory.builder()
                .itemId("00000001")
                .uitemId("00000")
                .salestrNo("6005")
                .build();

        Inventory oracleInventory = oracleRepository.getInventory(parameter);
        Inventory mysqlInventory = mysqlRepository.getInventory(parameter);

        log.debug("oracleInventory = {}", oracleInventory);
        log.debug("mysqlInventory = {}", mysqlInventory);

        return RepeatStatus.FINISHED;
    }
}
