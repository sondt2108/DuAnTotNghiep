package com.example.shop.repository;

import java.util.List;

import com.example.shop.models.ImgProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductImgRepository extends JpaRepository<ImgProduct, Long> {
    

    @Query(value = "select * from productimg where product_id= ?1", nativeQuery = true)
    List<ImgProduct> findByProductId(Long id);
}
