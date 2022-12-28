package com.example.shop.service.impl;

import com.example.shop.models.Review;
import com.example.shop.repository.ReviewRepository;
import com.example.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Override
    public List<Review> getAllReviewByProduct(int productId) {

        return reviewRepository.findReviewsByProductProductId(productId);
    }

    @Override
    public Review getReviewByPhoneNumberOrEmail(String phoneNumber, String email) {
        return null;
    }
}
