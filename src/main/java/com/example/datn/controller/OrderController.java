package com.example.datn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.datn.models.Product;
import com.example.datn.service.CartService;
import com.example.datn.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    CustomerService customerService;

	@Autowired
    CartService cartService;

    @GetMapping("checkout")
    public ResponseEntity<Map<String, Object>> checkout(Model model, HttpServletRequest request) {
		Map<Product, Integer> listItems = cartService.getCart().getCartDetails();
		Map<String, Object> result = new HashMap<>();
		//Get quantity product in cart
		listItems.keySet().stream().mapToInt(listItems::get).forEachOrdered(quantity -> result.put("quantity", quantity));
		if (customerService.isCustomerLogin()) {
			result.put("cart", cartService.getCart());
			result.put("name", customerService.getCustomer().getFullName());
			result.put("email",customerService.getCustomer().getUser().getEmail());
			result.put("phoneNumber",customerService.getCustomer().getPhoneNumber());
			result.put("address",customerService.getCustomer().getAddress());
			result.put("customer_id",customerService.getCustomer().getUser().getId());
			result.put("isLogin", true);
		} else {
			result.put("cart", cartService.getCart());
			result.put("isLogin", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
