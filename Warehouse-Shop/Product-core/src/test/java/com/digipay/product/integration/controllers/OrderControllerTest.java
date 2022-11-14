package com.digipay.product.integration.controllers;


import com.digipay.product.integration.AbstractTest;
import com.digipay.product.model.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderControllerTest extends AbstractTest {

    private final String baseUrl = "http://localhost:8081/api/v1/orders";


//    private String createdOrder = "http://localhost:8081/api/v1/orders/2c948a878424ae1a018424ae43060000";


    @Test
    @Order(1)
    void createOrderAndFindOrderById() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .header("Authorization",super.getBasicDigestHeaderValue())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(createDtoObject()))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        MvcResult findOrderByIdResult = mvc.perform(MockMvcRequestBuilders.get(mvcResult.getResponse().getHeader("location"))
                .header("Authorization",super.getBasicDigestHeaderValue())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(createDtoObject()))).andReturn();
        assertEquals(200,findOrderByIdResult.getResponse().getStatus());
    }


    OrderDto createDtoObject(){
        Set<Long> productIds = new HashSet<>();
        productIds.add(3L);
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId("2c948a87847793b0018477962b9e0000");
        orderDto.setProductsId(productIds);
        return orderDto;
    }



}