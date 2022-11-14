package com.digipay.product.service.impl;

import com.digipay.product.conf.mapper.AddressDtoMapper;
import com.digipay.product.model.dto.AddressDto;
import com.digipay.product.model.entity.Address;
import com.digipay.product.repository.AddressRepository;
import com.digipay.product.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressDtoMapper addressMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressDtoMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public Address saveAddress(AddressDto addressDto) {
        return addressRepository.save(addressMapper.dtoToAddressMapper(addressDto));
    }
}
