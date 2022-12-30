package com.spring.batch.mapper.oracle;

import com.spring.batch.domain.Inventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OracleMapper {

    List<Inventory> getInventories(Inventory inventory);

    Inventory getInventory();

    int insertInventory(Inventory inventory);
}
