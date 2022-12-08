package com.example.datn.controller;

import com.example.datn.models.Product;
import com.example.datn.payload.request.ProductRequest;
import com.example.datn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductRequest productRequest){

        productService.create(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProductRequest productRequest){
        productService.update(productRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{seoUrl}")
    public ResponseEntity<ProductRequest> getProductBySeoUrl(@PathVariable String seoUrl){
        Product product = productService.getProductBySeoUrl(seoUrl);
        if(ObjectUtils.isEmpty(product)){
            return ResponseEntity.notFound().build();
        }
        ProductRequest productRequest = ProductRequest.toProductRequest(product);
        return new ResponseEntity<>(productRequest, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductRequest> getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(ObjectUtils.isEmpty(product)){
            return ResponseEntity.notFound().build();
        }
        ProductRequest productRequest = ProductRequest.toProductRequest(product);
        return new ResponseEntity<>(productRequest, HttpStatus.OK);
    }
}
