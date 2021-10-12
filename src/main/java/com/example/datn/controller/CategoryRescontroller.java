package com.example.datn.controller;

import java.util.List;

import com.example.datn.models.Category;
import com.example.datn.repository.CategoryRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CategoryRescontroller {


@Autowired
CategoryRepository categoryRepository;

    
@GetMapping("api/categories")
public List<Category> listCategories(){

    return categoryRepository.findAll();
}



}
