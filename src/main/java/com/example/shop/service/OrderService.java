package com.example.shop.service;

import com.example.shop.payload.request.OrderRequest;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
}
