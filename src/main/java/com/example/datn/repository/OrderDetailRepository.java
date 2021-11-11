package com.example.datn.repository;

import java.util.List;
import java.util.Optional;

import com.example.datn.models.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(value = "SELECT * FROM order_detail where order_id = :order_id", nativeQuery = true)
	List<OrderDetail> findOrderItems(@Param(value = "order_id") Long orderID);


    
}
