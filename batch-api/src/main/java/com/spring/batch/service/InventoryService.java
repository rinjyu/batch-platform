package com.spring.batch.service;

import com.spring.batch.domain.Inventory;
import com.spring.batch.repository.MysqlRepository;
import com.spring.batch.repository.OracleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final MysqlRepository mysqlRepository;
    private final OracleRepository oracleRepository;

    public List<Inventory> getMysqlInventories(Inventory inventory) {
        return mysqlRepository.getInventories(inventory);
    }

    public List<Inventory> getOracleInventories(Inventory inventory) {
        return oracleRepository.getInventories(inventory);
    }

    public Inventory getMysqlInventory(Inventory inventory) {
        return mysqlRepository.getInventory(inventory);
    }

    public Inventory getOracleInventory(Inventory inventory) {
        return oracleRepository.getInventory(inventory);
    }

    public int insertMysqlInventory(Inventory inventory) {
        return mysqlRepository.insertInventory(inventory);
    }

    public int insertOracleInventory(Inventory inventory) {
        return oracleRepository.insertInventory(inventory);
    }
}