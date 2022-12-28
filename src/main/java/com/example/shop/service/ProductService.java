package com.example.shop.service;

import com.example.shop.models.Product;
import com.example.shop.models.SearchForm;
import com.example.shop.models.Trademark;
import com.example.shop.payload.request.ProductRequest;

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

    List<Product> getProductByCategory(String seoUrl);

    Product getProductBySeoUrl(String seoUrl);

    Product getProductByTrademark(Trademark trademark);

    void setProduct(Product product);
}
