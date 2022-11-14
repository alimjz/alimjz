package com.digipay.product.exception;

public class ErrorConstants {
    public static final String METHOD_NOT_ALLOWED = "Selected HTTP Method is Not Allowed for this Service.";
    public static final String DUPLICATE_CUSTOMER_REGISTRATION = "Customer Already Exists. Registration is not Allowed.";
    public static final String DUPLICATE_USER_REGISTRATION = "User Already Exists. Registration is not Allowed.";
    public static final String PRODUCT_NOT_FOUND = "Product Id Not Found or is not Available to Sell.";
    public static final String CUSTOMER_NOT_FOUND = "Customer Id Not Found.";

    private ErrorConstants() {
    }
}
