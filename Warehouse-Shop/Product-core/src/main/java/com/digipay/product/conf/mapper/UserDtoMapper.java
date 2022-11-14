package com.digipay.product.conf.mapper;

import com.digipay.product.model.dto.UserDto;
import com.digipay.product.model.entity.User;

//@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    User dtoToUserMapper(UserDto userDto);

}
