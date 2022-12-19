package com.example.datn.controller;

import com.example.datn.models.Product;
import com.example.datn.payload.request.ProductRequest;
import com.example.datn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/product")
    public ResponseEntity<Void> create(@RequestBody ProductRequest productRequest){

        productService.create(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/product")
    public ResponseEntity<Void> update(@RequestBody ProductRequest productRequest){
        productService.update(productRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{seoUrl}")
    public ResponseEntity<ProductRequest> getProductBySeoUrl(@PathVariable String seoUrl){
        Product product = productService.getProductBySeoUrl(seoUrl);
        ProductRequest productRequest = ProductRequest.toProductRequest(product);
        return new ResponseEntity<>(productRequest, HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductRequest> getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);
        ProductRequest productRequest = ProductRequest.toProductRequest(product);
        return new ResponseEntity<>(productRequest, HttpStatus.OK);
    }

    @GetMapping(value = "/categorySeoUrl/{seoUrl}")
    public ResponseEntity<List<ProductRequest>> getProductBySeoUrlCategory(@PathVariable String seoUrl){
        List<Product> products = productService.getProductByCategory(seoUrl);
        List<ProductRequest> productRequestList = ProductRequest.toProductRequestList(products);

        return new ResponseEntity<>(productRequestList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductRequest>> getAllProduct(){
        List<Product> products = productService.getAllProduct();
        List<ProductRequest> productRequestList = ProductRequest.toProductRequestList(products);

        return new ResponseEntity<>(productRequestList, HttpStatus.OK);
    }
}
