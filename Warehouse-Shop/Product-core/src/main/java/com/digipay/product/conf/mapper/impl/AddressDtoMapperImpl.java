package com.digipay.product.conf.mapper.impl;

import com.digipay.product.conf.mapper.AddressDtoMapper;
import com.digipay.product.model.dto.AddressDto;
import com.digipay.product.model.entity.Address;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-10-20T21:07:28+0330",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
@Component
public class AddressDtoMapperImpl implements AddressDtoMapper {

    @Override
    public Address dtoToAddressMapper(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }

        Address address = new Address();

        address.setAddressType(addressDto.getAddressType());
        address.setProvince(addressDto.getProvince());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setBuildingNo(addressDto.getBuildingNo());
        address.setPostalCode(addressDto.getPostalCode());

        return address;
    }

    @Override
    public AddressDto addressToDtoMapper(Address address) {
        if (address == null) {
            return null;
        }

        AddressDto addressDto = new AddressDto();


        addressDto.setAddressType(address.getAddressType());
        addressDto.setProvince(address.getProvince());
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setBuildingNo(address.getBuildingNo());
        addressDto.setPostalCode(address.getPostalCode());

        return addressDto;
    }
}
