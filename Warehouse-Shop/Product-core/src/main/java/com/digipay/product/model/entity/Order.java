package com.digipay.product.model.entity;

import com.digipay.product.enums.BusinessCode;
import com.digipay.product.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TBL_Orders")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonIgnore
    private String orderId;


    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_order_ID")
    @JsonManagedReference
    private List<SalesItem> salesItem;
    @NotNull
    private LocalDateTime createDate;
    private OrderStatus status;
    private BusinessCode businessCode;
    private LocalDateTime statusDate;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @PrePersist
    public void prePersistInitialize() {
        this.createDate = LocalDateTime.now();
        this.statusDate = LocalDateTime.now();
        this.status = OrderStatus.ACKNOWLEDGE;
    }

    @PostUpdate
    public void update() {
        this.statusDate = LocalDateTime.now();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }


    public List<SalesItem> getSalesItem() {
        return salesItem;
    }

    public void setSalesItem(List<SalesItem> salesItem) {
        this.salesItem = salesItem;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BusinessCode getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(BusinessCode businessCode) {
        this.businessCode = businessCode;
    }

    public LocalDateTime getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDateTime statusDate) {
        this.statusDate = statusDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
