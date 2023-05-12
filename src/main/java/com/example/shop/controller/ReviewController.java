package com.example.shop.controller;

import java.util.List;

import com.example.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.shop.payload.request.ReviewRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("api/review")
public class ReviewController {
    @Autowired
	ReviewService reviewService;

	
	@GetMapping("/{id}")
	public ResponseEntity<List<ReviewRequest>> getAll(@PathVariable("id") int id) {
		List<ReviewRequest> ratingRequestList = ReviewRequest.toRatingRequestList(reviewService.getAllReviewByProduct(id));

		return new ResponseEntity<>(ratingRequestList, HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid ReviewRequest reviewRequest) {
		reviewService.create(reviewRequest);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
