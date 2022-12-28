package com.example.shop.service;

import com.example.shop.models.Customer;
import com.example.shop.models.User;
import com.example.shop.payload.request.SignupRequest;

import java.util.List;

public interface CustomerService {
    Customer getCustomer();
    boolean isCustomerLogin();
    boolean getUser(Long userId);
    boolean logout(Long userId);
    void createCustomer(User user, SignupRequest signupRequest);
    List<Customer> getAllCustomer();
    Customer getCustomerById(int id);
}
