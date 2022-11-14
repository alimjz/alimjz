package com.digipay.product.conf.mapper.impl;

import com.digipay.product.conf.mapper.CustomerDtoMapper;
import com.digipay.product.enums.AddressType;
import com.digipay.product.model.dto.CustomerDto;
import com.digipay.product.model.entity.Address;
import com.digipay.product.model.entity.Contact;
import com.digipay.product.model.entity.ContactType;
import com.digipay.product.model.entity.Customer;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-10-20T21:07:28+0330",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
@Component
public class CustomerDtoMapperImpl implements CustomerDtoMapper {

    @Override
    public Customer dtoToCustomerMapper(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        Address address = new Address();
        address.setAddressType(AddressType.CUSTOMER);
        address.setProvince(customerDto.getContactInfo().getAddress().getProvince());
        address.setCity(customerDto.getContactInfo().getAddress().getCity());
        address.setStreet(customerDto.getContactInfo().getAddress().getStreet());
        address.setBuildingNo(customerDto.getContactInfo().getAddress().getBuildingNo());
        address.setPostalCode(customerDto.getContactInfo().getAddress().getPostalCode());

        Contact contact = new Contact();
        contact.setContactType(ContactType.CUSTOMER);
        contact.setEmail(customerDto.getContactInfo().getEmail());
        contact.setPhoneNumber(customerDto.getContactInfo().getPhoneNumber());
        contact.setAddress(address);


        Customer customer = new Customer();


        customer.setNationalId(customerDto.getNationalId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setBirthCertificateNo(customerDto.getBirthCertificateNo());
        customer.setBirthPlace(customerDto.getBirthPlace());
        customer.setContactInfo(contact);
        return customer;
    }

    @Override
    public CustomerDto customerToDtoMapper(Customer address) {
        if (address == null) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();


        customerDto.setNationalId(address.getNationalId());
        customerDto.setFirstName(address.getFirstName());
        customerDto.setLastName(address.getLastName());
        customerDto.setBirthDate(address.getBirthDate());
        customerDto.setBirthCertificateNo(address.getBirthCertificateNo());
        customerDto.setBirthPlace(address.getBirthPlace());
//        customerDto.setContactInfo(address.getContactInfo());

        return customerDto;
    }
}
