package com.example.shop.repository;

import com.example.shop.models.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    
}
