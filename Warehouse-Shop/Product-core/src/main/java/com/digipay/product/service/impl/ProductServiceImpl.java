package com.digipay.product.service.impl;

import com.digipay.product.conf.mapper.ProductDtoMapper;
import com.digipay.product.model.dto.ProductDto;
import com.digipay.product.model.entity.Product;
import com.digipay.product.repository.ProductRepository;
import com.digipay.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository prodRepository;
    private final ProductDtoMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository prodRepository, ProductDtoMapper productMapper) {
        this.prodRepository = prodRepository;
        this.productMapper = productMapper;
    }


    @Override
    @Transactional
    public Product stockInProducts(ProductDto productDto) {
        return prodRepository.save(productMapper.dtoToProductMapper(productDto));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAllProductsList(Pageable pageable) {
        return prodRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Product> findProductById(Long id) {
        return prodRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Product> findNonZeroQuantsProducts(Set<Long> productsId) {
        return deductQuantsByCount(prodRepository.findAllByProdIdInAndQuantsIsNot(productsId, 0), 1);
    }


    private List<Product> deductQuantsByCount(List<Product> productList, int count) {// TODO: 10/25/2022 will refactor
        List<Product> deductedQuantsProductList = new ArrayList<>();
        for (Product product : productList) {
            product.setQuants(product.getQuants() - count);
            deductedQuantsProductList.add(product);
        }
        return deductedQuantsProductList;
    }

}
