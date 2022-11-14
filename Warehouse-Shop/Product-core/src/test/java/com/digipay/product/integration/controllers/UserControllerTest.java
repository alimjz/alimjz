package com.digipay.product.integration.controllers;

import com.digipay.product.enums.AddressType;
import com.digipay.product.integration.AbstractTest;
import com.digipay.product.model.dto.AddressDto;
import com.digipay.product.model.dto.ContactDto;
import com.digipay.product.model.dto.UserDto;
import com.digipay.product.model.entity.ContactType;
import com.digipay.product.model.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest extends AbstractTest {

    private final String baseUrl = "http://localhost:8081/api/v1/users";


    @Test
    @Order(1)
    void createUserAndFindUserById() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .header("Authorization",super.getBasicDigestHeaderValue())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(createDtoObject()))).andReturn();
        log.info(mvcResult.getResponse().getHeader("location"));
        assertEquals(201, mvcResult.getResponse().getStatus());

        MvcResult findUserByUserId = mvc.perform(MockMvcRequestBuilders.get(mvcResult.getResponse().getHeader("location"))
                .header("Authorization",super.getBasicDigestHeaderValue())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(createDtoObject()))).andReturn();
        assertEquals(200,findUserByUserId.getResponse().getStatus());
    }

    private Object createDtoObject() {
        AddressDto address = new AddressDto();
        address.setAddressType(AddressType.USER);
        address.setProvince("test");
        address.setCity("test");
        address.setStreet("test");
        address.setBuildingNo("test");
        address.setPostalCode("4716614168");

        ContactDto contact = new ContactDto();
        contact.setContactType(ContactType.USER);
        contact.setEmail("a@b.com");
        contact.setPhoneNumber("test");
        contact.setAddress(address);

        UserDto userDto = new UserDto();
        userDto.setUserName("TestUser");
        userDto.setAccount("TestUser");
        userDto.setUserLastName("TestUser");
        userDto.setRole(Role.NORMAL);
        userDto.setContact(contact);
        userDto.setPassword("passwordtest");
        return userDto;
    }

}