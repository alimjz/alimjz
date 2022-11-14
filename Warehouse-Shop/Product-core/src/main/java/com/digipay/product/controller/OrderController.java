package com.digipay.product.controller;

import com.digipay.product.exception.CustomerNotFoundException;
import com.digipay.product.exception.ErrorConstants;
import com.digipay.product.model.cache.Cache;
import com.digipay.product.model.dto.BaseResponse;
import com.digipay.product.model.dto.OrderDto;
import com.digipay.product.model.entity.Order;
import com.digipay.product.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

import static com.digipay.product.conf.ApplicationConstants.*;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create an Order for sale.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SUCCESS,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "400", description = BADREQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Security Basic Auth needed", content = @Content)})
    @PostMapping("/orders")
    public ResponseEntity<BaseResponse<Order>> createOrder(@RequestBody OrderDto orderDto) {
        if (!Cache.customerCache.contains(orderDto.getCustomerId())) {
            Order savedOrder = orderService.createSalesOrder(orderDto);
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOrder.getOrderId())
                            .toUri()).
                    body(new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS));
        }
        throw new CustomerNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND);
    }

    @Operation(summary = "Query an Order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SUCCESS,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "400", description = BADREQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Security Basic Auth needed", content = @Content)})
    @GetMapping("/orders/{id}")
    public ResponseEntity<BaseResponse<Order>> findOrderById(@PathVariable String id) {
        Optional<Order> order = orderService.queryOrderById(id);
        return order.map(value -> ResponseEntity.ok().
                        body(new BaseResponse<>(HttpStatus.OK.value(), SUCCESS, null, value))).
                orElseGet(() -> new ResponseEntity<>(new BaseResponse<>(HttpStatus.NOT_FOUND.value(), NOT_FOUND,
                        null, null), HttpStatus.NOT_FOUND));
    }

}
