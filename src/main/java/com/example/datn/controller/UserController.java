package com.example.datn.controller;

import java.util.List;
import java.util.Map;

import com.example.datn.models.Category;
import com.example.datn.models.Product;
import com.example.datn.models.User;
import com.example.datn.repository.CategoryRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.service.CartService;
import com.example.datn.service.CustomerService;
import com.example.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class UserController {
    

	@Autowired
    CustomerService customerService;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CartService cartService;

	@GetMapping("/login")
	public String Login(Model model) {

		if (customerService.isCustomerLogin()) {
			
			return "redirect:/";
		} else {


			List<Category> categories = categoryRepository.findAll();
		model.addAttribute("cate", categories);


		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
		
	}
			
			
			return "dangnhap";
		}

		
	}


	@GetMapping("/register")
	public String Register(Model model) {

		if (customerService.isCustomerLogin()) {
			
			return "redirect:/";
		} else {
			
			List<Category> categories = categoryRepository.findAll();
		model.addAttribute("cate", categories);


		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
		
	}
			return "dangky";
		}
	}

	@GetMapping("/account/forgot-password")
	public String Forgotpassword(Model model) {

		if (customerService.isCustomerLogin()) {
			
			return "redirect:/";
		} else {
			
			List<Category> categories = categoryRepository.findAll();
		model.addAttribute("cate", categories);


		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
		
	}
	return "forgotpassword";
		}
	}

	
    @Autowired
    private UserService userService;
	

	@GetMapping("/account/reset-password")
	public String resetPassword(@RequestParam String token, Model model) {

		String response = userService.checkToken(token);

		if (!response.startsWith("Invalid")) {

			List<Category> categories = categoryRepository.findAll();
		model.addAttribute("cate", categories);


		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
		
	}

			return "resetpassword";

		} 

		return "404page";

		
	}
	

	@Autowired
	UserRepository pr;

	// @GetMapping("/user")
	// @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	// public List<User> list(){
		
		
		
	// 	return pr.findAll();
	// }

	// @GetMapping("/mod")
	// @PreAuthorize("hasRole('MODERATOR')")
	// public String moderatorAccess() {
	// 	return "Moderator Board.";
	// }

	// @GetMapping("/admin")
	// @PreAuthorize("hasRole('ADMIN')")
	// public String adminAccess() {
	// 	return "Admin Board.";
	// }
}
