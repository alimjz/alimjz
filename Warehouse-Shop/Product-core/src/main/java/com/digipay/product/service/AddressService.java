package com.digipay.product.service;

import com.digipay.product.model.dto.AddressDto;
import com.digipay.product.model.entity.Address;

public interface AddressService {
    Address saveAddress(AddressDto addressDto);
}
