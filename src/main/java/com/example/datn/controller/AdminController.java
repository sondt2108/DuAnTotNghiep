package com.example.datn.controller;

import com.example.datn.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {



    @Autowired
    CustomerService customerService;


    @GetMapping("admin/product")
    public String listProduct(){
      
           

        return "dashboard/products";
       

       
    }
    
}
