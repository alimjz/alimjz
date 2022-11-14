package com.digipay.product.repository;

import com.digipay.product.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    int countOrdersByCustomer_CustomerId(String customerId);
}
