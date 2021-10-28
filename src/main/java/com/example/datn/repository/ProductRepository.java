package com.example.datn.repository;

import java.util.List;
import java.util.Optional;

import com.example.datn.models.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
   


    @Query(value = "select * from products pr where thuonghieu_id = 1 and category_id = 1 Limit 6", nativeQuery = true)
    List<Product> findByTitleContaining();

    //@Query(value = "select * from products where seourl like ?1 ", nativeQuery = true)
    Optional<Product> findBySeourlContaining(String name);
}
