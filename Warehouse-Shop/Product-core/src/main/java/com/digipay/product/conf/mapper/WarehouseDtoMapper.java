package com.digipay.product.conf.mapper;

import com.digipay.product.model.dto.WarehouseDto;
import com.digipay.product.model.entity.Warehouse;

//@Mapper(componentModel = "spring")
public interface WarehouseDtoMapper {
    Warehouse dtoToWarehouseMapper(WarehouseDto warehouseDto);

    WarehouseDto warehouseToDtoMapper(Warehouse warehouse);
}
