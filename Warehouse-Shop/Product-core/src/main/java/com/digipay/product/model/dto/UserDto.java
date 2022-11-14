package com.digipay.product.model.dto;

import com.digipay.product.conf.BaseDto;
import com.digipay.product.model.entity.Role;
import com.digipay.product.validator.annotation.RoleValidation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto implements BaseDto {
    @NotBlank(message = "User Account should not be Null or Empty.")
    @Length(min = 4, max = 10, message = "User Account Must be 4 to 10 Characters.")
    private String account;
    @Length(min = 5, max = 15, message = "Password should be in range 5 to 15 Characters.")
    @NotBlank(message = "Password should not be Null or Empty.")
    private String password;// TODO: 10/27/2022 encrypt the password.
    @NotBlank(message = "User Name is Mandatory.")
    private String userName;
    @NotBlank(message = "User Last is Mandatory.")
    private String userLastName;
    @RoleValidation
    private Role role;

    private ContactDto contact;


}
