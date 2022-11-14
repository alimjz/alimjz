package com.digipay.product.conf.mapper;

import com.digipay.product.model.dto.InvoiceDto;
import com.digipay.product.model.entity.Invoice;

//@Mapper(componentModel = "spring")
public interface InvoiceDtoMapper {
    Invoice dtoToInvoiceMapper(InvoiceDto invoiceDto);

    InvoiceDto invoiceToDtoMapper(Invoice invoice);
}
