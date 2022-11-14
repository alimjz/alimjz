package com.digipay.product.repository;

import com.digipay.product.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByNationalId(String id);

    @Override
    Page<Customer> findAll(Pageable pageable);
}
