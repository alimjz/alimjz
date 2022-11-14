package com.digipay.product.service.impl;

import com.digipay.product.conf.mapper.OrderDtoMapper;
import com.digipay.product.enums.BusinessCode;
import com.digipay.product.exception.ProductNotFoundException;
import com.digipay.product.model.dto.OrderDto;
import com.digipay.product.model.entity.Customer;
import com.digipay.product.model.entity.Invoice;
import com.digipay.product.model.entity.Order;
import com.digipay.product.model.entity.Product;
import com.digipay.product.repository.OrderRepository;
import com.digipay.product.service.CustomerService;
import com.digipay.product.service.InvoiceService;
import com.digipay.product.service.OrderService;
import com.digipay.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

import static com.digipay.product.exception.ErrorConstants.CUSTOMER_NOT_FOUND;
import static com.digipay.product.exception.ErrorConstants.PRODUCT_NOT_FOUND;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderDtoMapper orderMapper;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, InvoiceService invoiceService
            , CustomerService customerService, ProductService productService, OrderDtoMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.productService = productService;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order createSalesOrder(OrderDto orderDto) {
        Optional<Customer> customer = customerService.findCustomerById(orderDto.getCustomerId());
        List<Product> products = productService.findNonZeroQuantsProducts(orderDto.getProductsId());
        Invoice invoice = invoiceService.calculateInvoice(products, orderDto.getCustomerId());
        if (!customer.isPresent()) {
            throw new NotFoundException(CUSTOMER_NOT_FOUND);
        } else if (products.size() != orderDto.getProductsId().size()) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        }
        Order order = orderMapper.convertDtoToOrder(orderDto, products, customer, invoice, BusinessCode.SALE);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> queryAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> queryOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public int countCustomerOrders(String id) {
        return orderRepository.countOrdersByCustomer_CustomerId(id);
    }
}
