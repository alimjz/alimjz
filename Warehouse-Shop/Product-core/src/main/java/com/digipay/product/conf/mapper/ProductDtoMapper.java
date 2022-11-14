package com.digipay.product.conf.mapper;

import com.digipay.product.model.dto.ProductDto;
import com.digipay.product.model.entity.Product;

//@Mapper(componentModel = "spring")
public interface ProductDtoMapper {
    Product dtoToProductMapper(ProductDto productDto);

    ProductDto productToDtoMapper(Product productDto);
}
