package com.digipay.product.conf.mapper.impl;

import com.digipay.product.conf.mapper.InvoiceDtoMapper;
import com.digipay.product.model.dto.InvoiceDto;
import com.digipay.product.model.entity.Invoice;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-10-20T21:07:28+0330",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
@Component
public class InvoiceDtoMapperImpl implements InvoiceDtoMapper {

    @Override
    public Invoice dtoToInvoiceMapper(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setInvoiceId(invoiceDto.getInvoiceId());
        invoice.setBaseFee(invoiceDto.getBaseFee());
        invoice.setTax(invoiceDto.getTax());
        invoice.setDiscountPercent(invoiceDto.getDiscountPercent());
        invoice.setDiscountAmount(invoiceDto.getDiscountAmount());
        invoice.setPayAbleAmount(invoiceDto.getPayAbleAmount());

        return invoice;
    }

    @Override
    public InvoiceDto invoiceToDtoMapper(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setBaseFee(invoice.getBaseFee());
        invoiceDto.setTax(invoice.getTax());
        invoiceDto.setDiscountPercent(invoice.getDiscountPercent());
        invoiceDto.setDiscountAmount(invoice.getDiscountAmount());
        invoiceDto.setPayAbleAmount(invoice.getPayAbleAmount());

        return invoiceDto;
    }
}
