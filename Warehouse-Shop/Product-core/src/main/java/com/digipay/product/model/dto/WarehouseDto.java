package com.digipay.product.model.dto;

import com.digipay.product.conf.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto implements BaseDto {
    private String warehouseName;
    private AddressDto address;
}
