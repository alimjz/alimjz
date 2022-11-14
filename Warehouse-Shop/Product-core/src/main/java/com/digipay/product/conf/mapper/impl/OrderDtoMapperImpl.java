package com.digipay.product.conf.mapper.impl;

import com.digipay.product.conf.mapper.OrderDtoMapper;
import com.digipay.product.enums.BusinessCode;
import com.digipay.product.enums.OrderStatus;
import com.digipay.product.model.dto.OrderDto;
import com.digipay.product.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class OrderDtoMapperImpl implements OrderDtoMapper {

    @Override
    public Order convertDtoToOrder(OrderDto order, List<Product> products, Optional<Customer> customer,
                                   Invoice invoice, BusinessCode businessCode) {
        if (order == null) {
            return null;
        }
        Order orderObj = new Order();
        orderObj.setCustomer(customer.get());
        orderObj.setSalesItem(convertProductsToSalesItem(products));
        orderObj.setInvoice(invoice);
        orderObj.setBusinessCode(BusinessCode.SALE);
        orderObj.setStatus(OrderStatus.ACKNOWLEDGE);
        orderObj.setBusinessCode(businessCode);
        return orderObj;
    }


    private List<SalesItem> convertProductsToSalesItem(List<Product> products) {
        List<SalesItem> salesItemList = new ArrayList<>();
        for (Product product : products) {
            SalesItem salesItem = new SalesItem();
            salesItem.setProductId(product.getProdId());
            salesItem.setProdName(product.getProdName());
            salesItem.setSellPrice(product.getSellPrice());
            salesItem.setProdCode(product.getProdCode());
            salesItem.setQuantity(1);
            salesItemList.add(salesItem);
        }
        return salesItemList;
    }

}
