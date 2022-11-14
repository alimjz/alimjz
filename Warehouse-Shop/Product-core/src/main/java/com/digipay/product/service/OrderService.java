package com.digipay.product.service;

import com.digipay.product.model.dto.OrderDto;
import com.digipay.product.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createSalesOrder(OrderDto orderDto);

    List<Order> queryAllOrders();

    Optional<Order> queryOrderById(String id);

    int countCustomerOrders(String id);
}
