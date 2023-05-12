package com.example.shop.payload.request;

import com.example.shop.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewRequest {
    private String name;

	private String description;
	
	private int scores;
	
	private int productId;
	
	private String phoneNumber;
	
	private String image;
	
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getScores() {
		return scores;
	}

	public void setScore(int scores) {
		this.scores = scores;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public static List<ReviewRequest> toRatingRequestList(List<Review> reviewList){
		List<ReviewRequest> ratingRequestList = new ArrayList<>();
		for(Review review : reviewList){
			ratingRequestList.add(toRatingRequest(review));
		}

		return ratingRequestList;
	}

	public static ReviewRequest toRatingRequest(Review review){
		ReviewRequest ratingRequest = new ReviewRequest();
		ratingRequest.setName(review.getName());
		ratingRequest.setEmail(review.getEmail());
		ratingRequest.setPhoneNumber(ratingRequest.getPhoneNumber());
		ratingRequest.setImage(ratingRequest.getImage());
		ratingRequest.setDescription(ratingRequest.getDescription());

		return ratingRequest;
	}

	
	
}
