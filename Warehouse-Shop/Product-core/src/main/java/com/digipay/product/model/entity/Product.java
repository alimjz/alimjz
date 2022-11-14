package com.digipay.product.model.entity;

import com.digipay.product.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_ID")
    private Long prodId;
    private String prodCode;
    private String prodName;
    private Double buyPrice;
    private Double sellPrice;
    @Column(name = "STATUS")
    private ProductStatus status;
    private String model;
    private String prodType;
    private String prodSubType;
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "WAREHOUSE_ID")
    @JsonIgnore
    private Warehouse warehouse;

    @Column(name = "QUANTITY")
    private int quants;


    @PrePersist
    public void initialize() {
        this.status = ProductStatus.AVAILABLE;
    }

    public int getQuants() {
        return quants;
    }

    public void setQuants(int quants) {
        this.quants = quants;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdSubType() {
        return prodSubType;
    }

    public void setProdSubType(String prodSubType) {
        this.prodSubType = prodSubType;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
