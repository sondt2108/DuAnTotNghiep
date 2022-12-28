package com.example.shop.controller;

import com.example.shop.payload.request.CustomerRequest;
import com.example.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerRequest>> getAllCustomer(){
        List<CustomerRequest> customerRequestList = CustomerRequest.toCustomerRequestList(customerService.getAllCustomer());

        return new ResponseEntity<>(customerRequestList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerRequest> getCustomerById(@PathVariable int id){
        CustomerRequest customerRequest = CustomerRequest.customerToCustomerRequest(customerService.getCustomerById(id));

        return new ResponseEntity<>(customerRequest, HttpStatus.OK);
    }

}
