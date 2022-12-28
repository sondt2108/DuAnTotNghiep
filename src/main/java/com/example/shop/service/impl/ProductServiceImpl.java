package com.example.shop.service.impl;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.models.Category;
import com.example.shop.models.Product;
import com.example.shop.models.SearchForm;
import com.example.shop.models.Trademark;
import com.example.shop.payload.request.ProductRequest;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final int PAGE_SIZE = 25;
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

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
        Product product = productRepository.findByProductId(id);
        if (ObjectUtils.isEmpty(product)){
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public List<Product> getProductByCategory(String seoUrl) {
        Category category = categoryService.getCategoryBySeoUrl(seoUrl);
        if(ObjectUtils.isEmpty(category)){
            throw new ResourceNotFoundException("Category not found");
        }
        List<Product> product = productRepository.findProductByCategory(category);
        if (ObjectUtils.isEmpty(product)){
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public Product getProductBySeoUrl(String seoUrl) {
        Product product = productRepository.findBySeoUrl(seoUrl);
        if (ObjectUtils.isEmpty(product)){
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public Product getProductByTrademark(Trademark trademark) {
        Product product = productRepository.findProductByTrademark(trademark);
        if (ObjectUtils.isEmpty(product)){
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public void setProduct(Product product) {
        productRepository.save(product);
    }
}
