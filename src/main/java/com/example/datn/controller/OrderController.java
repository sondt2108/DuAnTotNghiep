package com.example.datn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.datn.payload.response.JwtResponse;
import com.example.datn.service.CartService;
import com.example.datn.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @Autowired
    CustomerService customerService;

	@Autowired
    CartService cartService;

	
    
    @GetMapping("checkout")
    public String checkout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (customerService.isCustomerLogin()) {
			//model.addAttribute("cartStatus", 0);
			model.addAttribute("cart", cartService.getGioHang());
			model.addAttribute("total", cartService.getTotal().toString());
			model.addAttribute("name", customerService.getCustomer().getHoten());
			model.addAttribute("email",customerService.getCustomer().getUser().getEmail() );
			model.addAttribute("phoneNumber",customerService.getCustomer().getSdt());
			model.addAttribute("address",customerService.getCustomer().getDiachi());
			return "thanhtoan";
		} else {
			//model.addAttribute("cartStatus", 1);
			return "redirect:/login?cartStatus=0";
		}
	}
}
