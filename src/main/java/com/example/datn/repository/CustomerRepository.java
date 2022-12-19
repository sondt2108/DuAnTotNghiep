package com.example.datn.repository;

import com.example.datn.models.Customer;

import com.example.datn.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findByFullNameContaining(String fullName, Pageable pager);
    Customer findCustomerByUserId(Long userId);

    

    
}
