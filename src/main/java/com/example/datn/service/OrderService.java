package com.example.datn.service;

import com.example.datn.payload.request.OrderRequest;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
}
