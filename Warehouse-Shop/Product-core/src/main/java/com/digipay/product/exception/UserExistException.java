package com.digipay.product.exception;

public class UserExistException extends RuntimeException {
    public UserExistException(String message){
        super(message);
    }
}
