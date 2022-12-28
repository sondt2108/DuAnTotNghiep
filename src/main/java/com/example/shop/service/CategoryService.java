package com.example.shop.service;

import com.example.shop.models.Category;
import com.example.shop.models.SearchForm;
import com.example.shop.payload.request.CategoryRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    @PreAuthorize("hasRole('ADMIN')")
    void create(CategoryRequest categoryRequest);

    @PreAuthorize("hasRole('ADMIN')")
    void update(CategoryRequest categoryRequest);

    @PreAuthorize("hasRole('ADMIN')")
    void delete(int id);

    Category getCategoryById(int id);

    List<Category> getAllCategory();

    List<Category> search(SearchForm searchForm);

    Category getCategoryBySeoUrl(String seoUrl);
}
