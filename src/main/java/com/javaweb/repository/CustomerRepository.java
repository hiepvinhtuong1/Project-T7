package com.javaweb.repository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.custom.CustomerCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerCustom {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
