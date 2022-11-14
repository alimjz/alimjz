package com.digipay.product.conf.mapper.impl;

import com.digipay.product.conf.mapper.UserDtoMapper;
import com.digipay.product.enums.AddressType;
import com.digipay.product.model.dto.UserDto;
import com.digipay.product.model.entity.Address;
import com.digipay.product.model.entity.Contact;
import com.digipay.product.model.entity.ContactType;
import com.digipay.product.model.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-10-20T21:07:28+0330",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {


    @Override
    public User dtoToUserMapper(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        Address address = new Address();
        address.setAddressType(AddressType.USER);
        address.setProvince(userDto.getContact().getAddress().getProvince());
        address.setCity(userDto.getContact().getAddress().getCity());
        address.setStreet(userDto.getContact().getAddress().getStreet());
        address.setBuildingNo(userDto.getContact().getAddress().getBuildingNo());
        address.setPostalCode(userDto.getContact().getAddress().getPostalCode());

        Contact contact = new Contact();
        contact.setContactType(ContactType.USER);
        contact.setEmail(userDto.getContact().getEmail());
        contact.setAddress(address);
        contact.setPhoneNumber(userDto.getContact().getPhoneNumber());

        User user = new User();
        user.setAccount(userDto.getAccount());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setUserName(userDto.getUserName());
        user.setUserLastName(userDto.getUserLastName());
        user.setRole(userDto.getRole());
        user.setContact(contact);

        return user;
    }


}
