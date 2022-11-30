package com.example.datn.repository;

import com.example.datn.models.Trademark;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrademarkRepository extends JpaRepository<Trademark, Integer> {
    
}
