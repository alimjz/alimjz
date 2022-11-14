package com.digipay.product.repository;

import com.digipay.product.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByAccount(String account);
}
