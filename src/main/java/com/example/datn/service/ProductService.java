package com.example.datn.service;

import com.example.datn.models.Category;
import com.example.datn.models.Product;
import com.example.datn.models.SearchForm;
import com.example.datn.models.Trademark;
import com.example.datn.payload.request.ProductRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    @PreAuthorize("hasRole('ADMIN')")
    void create(ProductRequest productRequest);

    @PreAuthorize("hasRole('ADMIN')")
    void update(ProductRequest productRequest);

    Product getProductById(int id);

    List<Product> getAllProduct();

    List<Product> search(SearchForm searchForm);

    @PreAuthorize("hasRole('ADMIN')")
    void delete(int id);

    Product getProductByCategory(Category category);

    Product getProductBySeoUrl(String seoUrl);

    Product getProductByTrademark(Trademark trademark);
}
