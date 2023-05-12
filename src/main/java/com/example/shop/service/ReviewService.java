package com.example.shop.service;

import com.example.shop.models.Review;
import com.example.shop.payload.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewByProduct(int productId);
    Review getReviewByPhoneNumberOrEmail(String phoneNumber, String email);
    void create(ReviewRequest ratingRequest);
}
