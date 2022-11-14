package com.digipay.product.conf.mapper;

import com.digipay.product.model.dto.CustomerDto;
import com.digipay.product.model.entity.Customer;

//@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {
    Customer dtoToCustomerMapper(CustomerDto customerDto);

    CustomerDto customerToDtoMapper(Customer customer);
}
