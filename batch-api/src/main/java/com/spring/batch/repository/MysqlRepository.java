package com.spring.batch.repository;

import com.spring.batch.domain.Inventory;
import com.spring.batch.mapper.mysql.MysqlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MysqlRepository {

    private final MysqlMapper mysqlMapper;

    public List<Inventory> getInventories(Inventory inventory) {
        return mysqlMapper.getInventories(inventory);
    }

    public Inventory getInventory(Inventory inventory) {
        return mysqlMapper.getInventory();
    }

    public int insertInventory(Inventory inventory) {
        return mysqlMapper.insertInventory(inventory);
    }
}