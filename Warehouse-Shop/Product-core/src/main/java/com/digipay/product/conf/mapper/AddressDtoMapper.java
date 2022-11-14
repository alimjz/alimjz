package com.digipay.product.conf.mapper;

import com.digipay.product.model.dto.AddressDto;
import com.digipay.product.model.entity.Address;

//@Mapper(componentModel = "spring")
public interface AddressDtoMapper {
    Address dtoToAddressMapper(AddressDto addressDto);

    AddressDto addressToDtoMapper(Address address);
}
