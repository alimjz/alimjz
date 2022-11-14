package com.digipay.product.conf.mapper.impl;

import com.digipay.product.conf.mapper.WarehouseDtoMapper;
import com.digipay.product.enums.AddressType;
import com.digipay.product.model.dto.WarehouseDto;
import com.digipay.product.model.entity.Address;
import com.digipay.product.model.entity.Warehouse;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-10-20T21:07:27+0330",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
@Component
public class WarehouseDtoMapperImpl implements WarehouseDtoMapper {

    @Override
    public Warehouse dtoToWarehouseMapper(WarehouseDto warehouseDto) {
        if (warehouseDto == null) {
            return null;
        }

        Address address = new Address();
        address.setAddressType(AddressType.WAREHOUSE);
        address.setProvince(warehouseDto.getAddress().getProvince());
        address.setCity(warehouseDto.getAddress().getCity());
        address.setStreet(warehouseDto.getAddress().getStreet());
        address.setBuildingNo(warehouseDto.getAddress().getBuildingNo());
        address.setPostalCode(warehouseDto.getAddress().getPostalCode());
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName(warehouseDto.getWarehouseName());
        warehouse.setAddress(address);

        return warehouse;
    }

    @Override
    public WarehouseDto warehouseToDtoMapper(Warehouse warehouse) {
        if (warehouse == null) {
            return null;
        }

        WarehouseDto warehouseDto = new WarehouseDto();

        warehouseDto.setWarehouseName(warehouse.getWarehouseName());

        return warehouseDto;
    }
}
