package com.example.datn.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "customer")
public class Customer {
    @Id
	@GeneratedValue
	private int id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
	private User user;
	
    @NotBlank
	@Size(max = 35)
	private String fullName;
	
	@NotBlank
	@Size(max = 150)
	private String address;
	
	
	private String phoneNumber;

	public Customer() {
	}

	public Customer(int id, User user, String fullName, String address, String phoneNumber) {
		this.id = id;
		this.user = user;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
