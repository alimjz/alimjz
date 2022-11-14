package com.digipay.product.model.dto;

import com.digipay.product.conf.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class OrderDto implements BaseDto {

    @JsonIgnore
    private Long orderId;
    @NotBlank(message = "The Product field should not be Empty.")
    private Set<Long> productsId;
    @NotBlank(message = "The Customer Id Should not be Empty.")
    private String customerId;


}
