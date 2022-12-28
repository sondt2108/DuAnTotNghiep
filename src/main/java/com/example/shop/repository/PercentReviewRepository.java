package com.example.shop.repository;

import java.util.List;

import com.example.shop.models.PercentReview;
import com.example.shop.models.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PercentReviewRepository extends JpaRepository<Review, Integer>{

}