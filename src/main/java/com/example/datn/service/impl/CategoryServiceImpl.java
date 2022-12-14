package com.example.datn.service.impl;

import com.example.datn.exception.ResourceNotFoundException;
import com.example.datn.models.Category;
import com.example.datn.models.SearchForm;
import com.example.datn.payload.request.CategoryRequest;
import com.example.datn.repository.CategoryRepository;
import com.example.datn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final int PAGE_SIZE = 25;
    private static final String CATEGORY_NOT_FOUND = "Category not found";
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void create(CategoryRequest categoryRequest) {
        Category category = Category.toCategory(categoryRequest);

        categoryRepository.save(category);
    }

    @Override
    public void update(CategoryRequest categoryRequest) {
        Category category = Category.toCategory(categoryRequest);
        category.setCategoryId(categoryRequest.getId());
        categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        Category category = categoryRepository.findCategoryByCategoryId(id);
        if (ObjectUtils.isEmpty(category)){
            throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(int id) {
        Category category = categoryRepository.findCategoryByCategoryId(id);
        if (ObjectUtils.isEmpty(category)){
            throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
        }
        return category;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> search(SearchForm searchForm) {
        Pageable pageable = PageRequest.of(searchForm.getPage(), PAGE_SIZE, searchForm.isOrderly() ? Sort.Direction.ASC : Sort.Direction.DESC, searchForm.getOrderBy());
        Page<Category> categoryPage = categoryRepository.findCategoryByNameContainingIgnoreCase(searchForm.getName(), pageable);

        return categoryPage.toList();
    }

    @Override
    public Category getCategoryBySeoUrl(String seoUrl) {
        Category category =  categoryRepository.findCategoryBySeoUrl(seoUrl);
        if(ObjectUtils.isEmpty(category)){
            throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
        }
        return category;
    }
}
