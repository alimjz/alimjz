package com.digipay.product.service;

import com.digipay.product.model.dto.UserDto;
import com.digipay.product.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User saveUser(UserDto userDto);

    Optional<User> findUserByUserId(String id);

    Page<User> findAllUserPageable(Pageable pageable);
}
