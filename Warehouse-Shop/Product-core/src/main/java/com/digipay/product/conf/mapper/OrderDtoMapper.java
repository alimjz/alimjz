package com.digipay.product.conf.mapper;

import com.digipay.product.enums.BusinessCode;
import com.digipay.product.model.dto.OrderDto;
import com.digipay.product.model.entity.Customer;
import com.digipay.product.model.entity.Invoice;
import com.digipay.product.model.entity.Order;
import com.digipay.product.model.entity.Product;

import java.util.List;
import java.util.Optional;

//@Mapper(componentModel = "spring")
public interface OrderDtoMapper {
    Order convertDtoToOrder(OrderDto order, List<Product> products, Optional<Customer> customer,
                            Invoice invoice, BusinessCode businessCode);

}
