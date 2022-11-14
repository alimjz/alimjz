package com.digipay.product.model.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Data
public class BaseResponse<T> {

    private int status;
    private String description;

    private Map<String, String> errors;

    private T response;

    private Pageable pageable;


    public BaseResponse(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public BaseResponse(int status, String description, Map<String, String> errors) {
        this(status, description);
        this.errors = errors;
    }

    public BaseResponse(int status, String description, Map<String, String> errors, T response) {
        this(status, description, errors);
        this.response = response;
    }

    public BaseResponse(int status, String description, Map<String, String> errors, T response, Pageable pageable) {
        this(status, description, errors);
        this.response = response;
        this.pageable = pageable;
    }

}
