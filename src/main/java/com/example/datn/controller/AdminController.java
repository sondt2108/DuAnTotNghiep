package com.example.datn.controller;

import com.example.datn.models.Role;
import com.example.datn.payload.response.JwtResponse;
import com.example.datn.security.services.UserDetailsImpl;
import com.example.datn.service.CustomerService;
import com.example.datn.service.UserService;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {



    @Autowired
    UserService userService;


    @GetMapping("admin/product")
    public String listProduct(){
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }
         return "dashboard/products"; 
    }
    

    @GetMapping("admin/category")
    public String listCategory(){
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }
         return "dashboard/categories"; 
    }
}
