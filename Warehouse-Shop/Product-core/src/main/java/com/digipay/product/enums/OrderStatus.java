package com.digipay.product.enums;

public enum OrderStatus {
    /**
     * it is used to define order status.
     * acknowledege means it is initiated.
     * inprogress means it is ongoing.
     * canceled means the order was canceled.
     * completed means order finished successfully.
     */
    ACKNOWLEDGE,
    INPROGRESS,
    CANCELED,
    COMPLETED
}