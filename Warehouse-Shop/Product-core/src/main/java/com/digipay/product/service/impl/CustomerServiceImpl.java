package com.digipay.product.service.impl;

import com.digipay.product.conf.mapper.CustomerDtoMapper;
import com.digipay.product.exception.CustomerExistException;
import com.digipay.product.exception.ErrorConstants;
import com.digipay.product.model.cache.Cache;
import com.digipay.product.model.dto.CustomerDto;
import com.digipay.product.model.entity.Customer;
import com.digipay.product.repository.CustomerRepository;
import com.digipay.product.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDtoMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer registerCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.dtoToCustomerMapper(customerDto);
        if (!Cache.customerCache.contains(customer.getNationalId())){
            Cache.customerCache.add(customer.getNationalId());
            return customerRepository.save(customer);
        }
        throw new CustomerExistException(ErrorConstants.DUPLICATE_CUSTOMER_REGISTRATION);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Optional<Customer> findCustomerByCertificate(String id) {
        return customerRepository.findByNationalId(id);
    }

    public Optional<Customer> findCustomerById(String id) {
        return customerRepository.findById(id);
    }


}
