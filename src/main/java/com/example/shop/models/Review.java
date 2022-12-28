package com.example.shop.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "review")
public class Review {
	
	@Id
	@GeneratedValue
	int id;
	String name;
	String description;
	int scores;
	String image;
	
	String gmail;
	
	String phoneNumber;
	
	@NotNull
	private Date createdDate = new Date((new java.util.Date()).getTime());
	
	@ManyToOne
	@JoinColumn(name = "productId", nullable = true, foreignKey = @ForeignKey(name = "review_product"))
	Product product;
	
	
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int id, String name, String description, int scores, String image, String gmail, String phoneNumber, Date createdDate, Product product) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.scores = scores;
		this.image = image;
		this.gmail = gmail;
		this.phoneNumber = phoneNumber;
		this.createdDate = createdDate;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public double getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	
	
	
}