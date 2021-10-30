package com.example.datn.repository;

import com.example.datn.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select * from khachhang  where user_id = ?1 ", nativeQuery = true)
    Customer findByUserId(@Param(value = "user_id") Long userId);

    
}
