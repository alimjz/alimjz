package com.digipay.product.exception;

import com.digipay.product.model.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.digipay.product.conf.ApplicationConstants.BADREQUEST;
import static com.digipay.product.exception.ErrorConstants.*;

@RestControllerAdvice
public class ExceptionTranslator {
    private static final String TITLE = "description";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(),
                BADREQUEST, errors));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, METHOD_NOT_ALLOWED);
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.METHOD_NOT_ALLOWED.value(),
                BADREQUEST, map));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerExistException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> customerDuplicationHandler(CustomerExistException ex) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, DUPLICATE_CUSTOMER_REGISTRATION);
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(),
                BADREQUEST, map));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> productNotFoundHandler(ProductNotFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, PRODUCT_NOT_FOUND);
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.NOT_FOUND.value(),
                PRODUCT_NOT_FOUND, map));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> customerNotFoundHandler(CustomerNotFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, CUSTOMER_NOT_FOUND);
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.NOT_FOUND.value(),
                CUSTOMER_NOT_FOUND, map));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>>
    unsatisfiedServletRequestParameterExceptionHandler(UnsatisfiedServletRequestParameterException ex) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(),
                BADREQUEST, map));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> userExistExceptionHandler(UserExistException ex) {
        Map<String, String> map = new HashMap<>();
        map.put(TITLE, ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(),
                BADREQUEST, map));
    }

}
