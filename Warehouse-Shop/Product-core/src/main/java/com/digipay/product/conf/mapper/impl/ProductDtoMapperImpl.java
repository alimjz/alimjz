package com.digipay.product.conf.mapper.impl;

import com.digipay.product.conf.mapper.ProductDtoMapper;
import com.digipay.product.model.dto.ProductDto;
import com.digipay.product.model.entity.Product;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-10-20T20:29:58+0330",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
@Component
public class ProductDtoMapperImpl implements ProductDtoMapper {

    @Override
    public Product dtoToProductMapper(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        Product product = new Product();

        product.setProdId(productDto.getProdId());
        product.setProdCode(productDto.getProdCode());
        product.setProdName(productDto.getProdName());
        product.setBuyPrice(productDto.getBuyPrice());
        product.setSellPrice(productDto.getSellPrice());
        product.setModel(productDto.getModel());
        product.setProdType(productDto.getProdType());
        product.setProdSubType(productDto.getProdSubType());
        product.setQuants(productDto.getQuants());

        return product;
    }

    @Override
    public ProductDto productToDtoMapper(Product productDto) {
        if (productDto == null) {
            return null;
        }

        ProductDto productDto1 = new ProductDto();

        productDto1.setProdId(productDto.getProdId());
        productDto1.setProdCode(productDto.getProdCode());
        productDto1.setProdName(productDto.getProdName());
        productDto1.setBuyPrice(productDto.getBuyPrice());
        productDto1.setSellPrice(productDto.getSellPrice());
        productDto1.setModel(productDto.getModel());
        productDto1.setProdType(productDto.getProdType());
        productDto1.setProdSubType(productDto.getProdSubType());

        return productDto1;
    }
}
