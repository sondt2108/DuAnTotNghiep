package com.example.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shop.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
        List<Review> findReviewsByProductProductId(int productId);




    }

