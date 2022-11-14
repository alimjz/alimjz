package com.digipay.product.repository;

import com.digipay.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByProdIdInAndQuantsIsNot(Set<Long> productIds, int quantity);

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    List<Product> findAll(Sort sort);

    @Override
    List<Product> findAll();
}
