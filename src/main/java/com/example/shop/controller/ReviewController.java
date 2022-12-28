package com.example.shop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.shop.models.Product;
import com.example.shop.models.Review;
import com.example.shop.payload.request.RatingRequest;
import com.example.shop.payload.response.MessageResponse;
import com.example.shop.repository.PercentReviewRepository;
import com.example.shop.repository.ReviewRepository;

@RestController
@RequestMapping("api/review")
public class ReviewController {
    @Autowired
	ReviewService reviewService;

	
	@GetMapping("/{id}")
	public ResponseEntity<List<RatingRequest>> getAll(@PathVariable("id") int id) {
		List<RatingRequest> ratingRequestList = RatingRequest.toRatingRequestList(reviewService.getAllReviewByProduct(id));

		return new ResponseEntity<>(ratingRequestList, HttpStatus.OK);
	}

	
//	@PostMapping
//	public ResponseEntity<Void> create(@RequestBody @Valid RatingRequest ratingRequest) {
//
//		String phoneNumber = ratingRequest.getPhoneNumber();
//		int productId = ratingRequest.getProductId();
//
//		Optional<Review> reviewOptional = Optional
//				.ofNullable(reviewRepository.findByReview(phoneNumber, productId));
//
//		if (reviewOptional.isEmpty()) {
//			Product pr = new Product();
//			pr.setProductId(ratingRequest.getProductId());
//			Review rw = new Review();
//			rw.setName(ratingRequest.getName());
//			rw.setProduct(pr);
//			rw.setDescription(ratingRequest.getDescription());
//			rw.setGmail(ratingRequest.getGmail());
//			rw.setPhoneNumber(ratingRequest.getPhoneNumber());
//			rw.setScores(ratingRequest.getScores());
//			rw.setImage(ratingRequest.getImage());
//			reviewRepository.save(rw);
//
//			return ResponseEntity.ok(new MessageResponse("successfully!"));
//		}
//
//
//
//		return ResponseEntity.ok(new MessageResponse("spam kia!"));
//	}
	
}
