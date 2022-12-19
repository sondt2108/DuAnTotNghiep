package com.example.datn.service.impl;

import com.example.datn.models.Customer;
import com.example.datn.models.User;
import com.example.datn.payload.request.SignupRequest;
import com.example.datn.repository.CustomerRepository;


import com.example.datn.repository.UserRepository;
import com.example.datn.service.CustomerService;
import com.example.datn.service.CustomerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
	CustomerSession customerSession;

	@Autowired
	UserRepository userRepository;


    @Autowired
	CustomerRepository customerRepository;

    @Override
	public Customer getCustomer() {
		return customerSession.getCustomer();
	}

    @Override
	public boolean isCustomerLogin() {
		return customerSession.getCustomer() != null;
	}

	@Override
	public boolean logout(Long userId) {
		customerSession.setCustomer(null);
		return true;
	}


    @Override
	public boolean getUser(Long userId) {
		Customer customer = customerRepository.findCustomerByUserId(userId);
		if(customer != null) {
			customerSession.setCustomer(customer);
			return true;
		}
		return false;
	}

	@Override
	public Customer createCustomer(User user, SignupRequest signupRequest) {
		Customer customer = new Customer();
		customer.setUser(user);
		customer.setFullName(signupRequest.getName());
		customer.setAddress(signupRequest.getAddress());
		return customerRepository.save(customer);
	}
}
