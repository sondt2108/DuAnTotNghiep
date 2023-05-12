package com.example.shop.service.impl;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.models.Product;
import com.example.shop.models.Review;
import com.example.shop.payload.request.ReviewRequest;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ReviewRepository;
import com.example.shop.service.ReviewService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Review> getAllReviewByProduct(int productId) {

        return reviewRepository.findReviewByProductProductId(productId);
    }

    @Override
    public Review getReviewByPhoneNumberOrEmail(String phoneNumber, String email) {
        return reviewRepository.findReviewByPhoneNumberAndAndGmail(phoneNumber, email);
    }

    @Override
    public void create(ReviewRequest reviewRequest) {
        String phoneNumber = reviewRequest.getPhoneNumber();
        int productId = reviewRequest.getProductId();
        Review review = reviewRepository.findReviewByPhoneNumberAndProduct_ProductId(phoneNumber,productId);
        if(ObjectUtils.isEmpty(review)){
            review = new Review();
            review.setName(reviewRequest.getName());
            Product product = productRepository.findByProductId(reviewRequest.getProductId());
            if (ObjectUtils.isEmpty(product)){
                throw new ResourceNotFoundException("Product not found.");
            }
            review.setProduct(product);
            review.setDescription(reviewRequest.getDescription());
            review.setEmail(reviewRequest.getEmail());
            review.setPhoneNumber(reviewRequest.getPhoneNumber());
            review.setScores(reviewRequest.getScores());
            review.setImage(reviewRequest.getImage());

            reviewRepository.save(review);
        }
    }
}
