package com.example.datn.service.impl;

import com.example.datn.models.Category;
import com.example.datn.models.Product;
import com.example.datn.models.SearchForm;
import com.example.datn.models.Trademark;
import com.example.datn.payload.request.ProductRequest;
import com.example.datn.repository.ProductRepository;
import com.example.datn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final int PAGE_SIZE = 25;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void create(ProductRequest productRequest) {
        Product product = Product.toProduct(productRequest);
        productRepository.save(product);
    }

    @Override
    public void update(ProductRequest productRequest) {
        Product product = Product.toProduct(productRequest);
        product.setProductId(productRequest.getProductId());
        productRepository.save(product);
    }

    @Override
    public void delete(int id) {

        productRepository.deleteById(id);
    }

    @Override
    public List<Product> search(SearchForm searchForm) {
        Pageable pageable = PageRequest.of(searchForm.getPage(), PAGE_SIZE, searchForm.isOrderly() ? Sort.Direction.ASC : Sort.Direction.DESC, searchForm.getOrderBy());
        Page<Product> productPage = productRepository.findByProductNameContainingIgnoreCase(searchForm.getName(), pageable);

        return productPage.toList();
    }

    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {

        return productRepository.findByProductId(id);
    }

    @Override
    public Product getProductByCategory(Category category) {

        return productRepository.findProductByCategory(category);
    }

    @Override
    public Product getProductBySeoUrl(String seoUrl) {

        return productRepository.findBySeoUrl(seoUrl);
    }

    @Override
    public Product getProductByTrademark(Trademark trademark) {

        return productRepository.findProductByTrademark(trademark);
    }
}
