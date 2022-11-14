package com.digipay.product.exception;

public class CustomerExistException extends RuntimeException {
    public CustomerExistException(String message) {
        super(message);
    }
}
