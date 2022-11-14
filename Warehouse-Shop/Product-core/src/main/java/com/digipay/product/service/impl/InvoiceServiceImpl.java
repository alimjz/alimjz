package com.digipay.product.service.impl;

import com.digipay.product.conf.mapper.InvoiceDtoMapper;
import com.digipay.product.model.dto.InvoiceDto;
import com.digipay.product.model.entity.Invoice;
import com.digipay.product.model.entity.Product;
import com.digipay.product.repository.OrderRepository;
import com.digipay.product.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ConfigurationProperties(prefix = "app.sell")
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    private static final Double TAX_RATE = 0.09;

    private final InvoiceDtoMapper invoiceMapper;


    private final OrderRepository orderRepository;


    @Autowired
    public InvoiceServiceImpl(InvoiceDtoMapper invoiceMapper, OrderRepository orderRepository) {
        this.invoiceMapper = invoiceMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public Invoice calculateInvoice(List<Product> products, String customerId) {
        double totalPrice = 0;
        double totalTax = 0;
        double discountPercent = calculateDiscount(orderRepository.countOrdersByCustomer_CustomerId(customerId));
        for (Product product :
                products) {
            totalPrice += product.getSellPrice();
            totalTax += (product.getSellPrice() * TAX_RATE);
        }

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setBaseFee(totalPrice);
        invoiceDto.setTax(totalTax);
        invoiceDto.setDiscountPercent(discountPercent);
        invoiceDto.setDiscountAmount((totalPrice + totalTax) * discountPercent);
        invoiceDto.setPayAbleAmount(totalPrice + totalTax - invoiceDto.getDiscountAmount());
        log.info(invoiceDto.toString());
        return invoiceMapper.dtoToInvoiceMapper(invoiceDto);

    }

    @Override
    public Invoice calculateInvoiceForSales(List<Product> products, String customerId) {
        return calculateInvoice(products, customerId);
    }

    @Override
    public Optional<Invoice> queryInvoice(String id) {
        return Optional.empty();
    }

    private Double calculateDiscount(int orderCount) {
        if (orderCount <= 5)
            return 0D;
        else if (orderCount > 6 && orderCount < 11)
            return 0.03D;
        else
            return 0.05D;
    }


}
