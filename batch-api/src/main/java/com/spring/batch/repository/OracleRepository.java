package com.spring.batch.repository;

import com.spring.batch.domain.Inventory;
import com.spring.batch.mapper.oracle.OracleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OracleRepository {

    private final OracleMapper oracleMapper;

    public List<Inventory> getInventories(Inventory inventory) {
        return oracleMapper.getInventories(inventory);
    }

    public Inventory getInventory(Inventory inventory) {
        return oracleMapper.getInventory();
    }

    public int insertInventory(Inventory inventory) {
        return oracleMapper.insertInventory(inventory);
    }
}
