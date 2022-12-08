package com.example.datn.repository;

import com.example.datn.models.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryByCategoryId(int id);
    Page<Category> findCategoryByNameContainingIgnoreCase(String name, Pageable pageable);
}
