package com.spring.batch.mapper.mysql;

import com.spring.batch.domain.Inventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MysqlMapper {

    List<Inventory> getInventories(Inventory inventory);

    Inventory getInventory();

    int insertInventory(Inventory inventory);
}
