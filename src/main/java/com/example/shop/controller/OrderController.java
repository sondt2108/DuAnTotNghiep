package com.example.shop.controller;

import javax.validation.Valid;

import com.example.shop.payload.request.OrderRequest;

import com.example.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

    @GetMapping("checkout")
	public ResponseEntity<Void> checkoutByUser(@Valid @RequestBody OrderRequest orderRequest){
		orderService.createOrder(orderRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
