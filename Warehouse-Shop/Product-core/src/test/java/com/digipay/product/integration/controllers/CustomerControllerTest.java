package com.digipay.product.integration.controllers;


import com.digipay.product.enums.AddressType;
import com.digipay.product.integration.AbstractTest;
import com.digipay.product.model.dto.AddressDto;
import com.digipay.product.model.dto.ContactDto;
import com.digipay.product.model.dto.CustomerDto;
import com.digipay.product.model.entity.ContactType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest extends AbstractTest {

    private final String baseUrl = "http://localhost:8081/api/v1/customers";


    @Test
    @Order(1)
    void createAndFindById_Customer() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .header("Authorization", super.getBasicDigestHeaderValue())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(createDtoObject()))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        MvcResult findResult = mvc.perform(MockMvcRequestBuilders.get(mvcResult.getResponse().getHeader("location"))
                .header("Authorization", super.getBasicDigestHeaderValue())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int findStatus = findResult.getResponse().getStatus();
        assertEquals(200, findStatus);
    }


    @Test
    @Order(2)
    void findAllCustomer() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl)
                .header("Authorization", super.getBasicDigestHeaderValue())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }


    CustomerDto createDtoObject() {
        AddressDto address = new AddressDto();
        address.setAddressType(AddressType.CUSTOMER);
        address.setProvince("test");
        address.setCity("test");
        address.setStreet("test");
        address.setBuildingNo("test");
        address.setPostalCode("4716614168");

        ContactDto contact = new ContactDto();
        contact.setContactType(ContactType.CUSTOMER);
        contact.setEmail("a@b.com");
        contact.setPhoneNumber("test");
        contact.setAddress(address);


        CustomerDto customer = new CustomerDto();

        customer.setNationalId((String) UUID.randomUUID().toString().replace("_", "").subSequence(0, 10));
        customer.setFirstName("test");
        customer.setLastName("test");
        customer.setBirthDate(LocalDate.of(1993, 12, 28));
        customer.setBirthCertificateNo("test");
        customer.setBirthPlace("test");
        customer.setContactInfo(contact);
        return customer;
    }


}