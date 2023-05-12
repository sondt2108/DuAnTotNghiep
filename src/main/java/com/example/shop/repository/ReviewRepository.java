package com.example.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shop.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
        List<Review> findReviewByProductProductId(int productId);
        Review findReviewByPhoneNumberAndAndGmail(String phoneNumber, String email);
        Review findReviewByPhoneNumberAndProduct_ProductId(String phoneNumber, int productId);
    }

