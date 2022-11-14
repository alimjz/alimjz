package com.digipay.product.integration.controllers;


import com.digipay.product.enums.AddressType;
import com.digipay.product.integration.AbstractTest;
import com.digipay.product.model.dto.AddressDto;
import com.digipay.product.model.dto.ProductDto;
import com.digipay.product.model.dto.WarehouseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest extends AbstractTest {
    private final String baseUrl = "http://localhost:8081/api/v1/products";
    private String createdProduct = "http://localhost:8081/api/v1/products/";


    @Test
    @Order(1)
    void createProduct() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl).header("Authorization", super.getBasicDigestHeaderValue())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(createProductDto()))).andReturn();
        this.createdProduct = mvcResult.getResponse().getHeader("location");
        log.info(createdProduct);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        MvcResult mvcFindResult = mvc.perform(MockMvcRequestBuilders.get(createdProduct).header("Authorization", super.getBasicDigestHeaderValue())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int findStatus = mvcFindResult.getResponse().getStatus();
        assertEquals(200, findStatus);
    }



    @Test
    @Order(2)
    void findAllProducts() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl).header("Authorization", super.getBasicDigestHeaderValue())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }


    private Object createProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setProdName("Test-Product" + UUID.randomUUID().toString().replace("_", "").substring(0, 3));
        productDto.setModel("Test-" + UUID.randomUUID().toString().replace("_", "").substring(0, 3));
        productDto.setWarehouseDto(new WarehouseDto("main",
                new AddressDto(AddressType.WAREHOUSE, "tehran", "tehran", "tehran", "tehran", "tehran")));
        productDto.setBuyPrice(2000D);
        productDto.setSellPrice(2500D);
        productDto.setQuants(100);
        productDto.setProdCode("121212");
        productDto.setProdSubType("Smart");
        productDto.setProdType("Phone");
        return productDto;
    }
}