package com.example.datn.service;

import com.example.datn.models.Category;
import com.example.datn.models.SearchForm;
import com.example.datn.payload.request.CategoryRequest;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

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

}
