package com.example.shop.service.impl;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.models.Customer;
import com.example.shop.models.User;
import com.example.shop.payload.request.SignupRequest;
import com.example.shop.repository.CustomerRepository;


import com.example.shop.repository.UserRepository;
import com.example.shop.service.CustomerService;
import com.example.shop.service.CustomerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

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
	public void createCustomer(User user, SignupRequest signupRequest) {
		Customer customer = new Customer();
		customer.setUser(user);
		customer.setFullName(signupRequest.getName());
		customer.setAddress(signupRequest.getAddress());
        customerRepository.save(customer);
    }

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer customer = customerRepository.findCustomerById(id);
		if(ObjectUtils.isEmpty(customer)){
			throw new ResourceNotFoundException("Customer not found!");
		}
		return customer;
	}


}
