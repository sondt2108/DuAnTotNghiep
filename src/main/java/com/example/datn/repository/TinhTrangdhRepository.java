package com.example.datn.repository;

import com.example.datn.models.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TinhTrangdhRepository extends JpaRepository<OrderStatus, Integer> {
    
}
