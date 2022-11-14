package com.digipay.product.service;

import com.digipay.product.model.dto.WarehouseDto;
import com.digipay.product.model.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WarehouseService {

    Warehouse saveWarehouse(WarehouseDto warehouseDto);

    Optional<Warehouse> findWarehouseById(String id);

    Page<Warehouse> findAllWarehousePageable(Pageable pageable);
}
