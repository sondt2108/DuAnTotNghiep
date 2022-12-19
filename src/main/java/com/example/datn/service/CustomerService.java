package com.example.datn.service;

import com.example.datn.models.Customer;
import com.example.datn.models.User;
import com.example.datn.payload.request.SignupRequest;

public interface CustomerService {
    Customer getCustomer();
    boolean isCustomerLogin();
    boolean getUser(Long userId);
    boolean logout(Long userId);
    Customer createCustomer(User user, SignupRequest signupRequest);
}
