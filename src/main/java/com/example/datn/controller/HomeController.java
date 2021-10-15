package com.example.datn.controller;

import java.util.List;
import java.util.ArrayList;

import com.example.datn.models.Product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.datn.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class HomeController {
    

    

    @GetMapping("/demo")
    public String demo(){

        return "demo";
    }

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String po( @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "6") int size, Model model) {

    List<Product> products = new ArrayList<Product>();
      Pageable paging = PageRequest.of(page, size);
      
      Page<Product> pageTuts;
     
        pageTuts = productRepository.findByTitleContaining(paging);
      
        products = pageTuts.getContent();

        model.addAttribute("products", products);

        return "index";
    }
}
