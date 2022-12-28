package com.example.shop.service;

import com.example.shop.models.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewByProduct(int productId);
    Review getReviewByPhoneNumberOrEmail(String phoneNumber, String email);
}
