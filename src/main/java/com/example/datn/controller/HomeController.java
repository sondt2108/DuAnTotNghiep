package com.example.datn.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import com.example.datn.models.Product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.datn.repository.ProductRepository;
import com.example.datn.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class HomeController {
    

    @Autowired
	CartService cartService;

    @GetMapping("/detail/{productname}")
    public String demo(@PathVariable String productname, Model model){
        model.addAttribute("cart", cartService.getGioHang());
        return "demo";
    }

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String po(Model model) {

    List<Product> products = productRepository.findByTitleContaining();

        model.addAttribute("products", products);

        return "index";
    }


    @GetMapping("/checkout/success")
    public String oderSuccess(){

        return "ordersuccess";
    }


    
	
    
    

   



  
}
