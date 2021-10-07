package com.example.datn.controller;

import java.util.List;

import com.example.datn.models.User;
import com.example.datn.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class UserController {
    @GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}



	@GetMapping("/login")
	public String Login() {

		return "dangnhap";
	}


	@GetMapping("/register")
	public String Register() {

		return "dangky";
	}
	
	

	@Autowired
	UserRepository pr;

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<User> list(){
		
		
		
		return pr.findAll();
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
