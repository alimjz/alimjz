package com.digipay.product.service;

import com.digipay.product.model.dto.CustomerDto;
import com.digipay.product.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {
    Customer registerCustomer(CustomerDto customerDto);

    Page<Customer> findAllCustomers(Pageable pageable);

    Optional<Customer> findCustomerByCertificate(String id);

    Optional<Customer> findCustomerById(String id);
}
