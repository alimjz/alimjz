package com.digipay.product.model.dto;

import com.digipay.product.conf.BaseDto;
import com.digipay.product.model.entity.ContactType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class ContactDto implements BaseDto {

    @JsonIgnore
    private ContactType contactType;
    @NotNull(message = "Phone number is mandatory.")
    private String phoneNumber;
    @Email(message = "The Email format is not correct.")
    private String email;
    private AddressDto address;

}
