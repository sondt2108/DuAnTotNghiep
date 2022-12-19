package com.example.datn.controller;

import javax.validation.Valid;

import com.example.datn.payload.request.OrderRequest;
import com.example.datn.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/checkout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> checkoutByUser(@Valid @RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
