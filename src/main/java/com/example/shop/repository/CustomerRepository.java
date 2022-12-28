package com.example.shop.repository;

import com.example.shop.models.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findByFullNameContaining(String fullName, Pageable pager);
    Customer findCustomerByUserId(Long userId);
    Customer findCustomerById(int id);

    

    
}
