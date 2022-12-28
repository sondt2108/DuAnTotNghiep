package com.example.shop.repository;

import com.example.shop.models.Trademark;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrademarkRepository extends JpaRepository<Trademark, Integer> {
    
}
