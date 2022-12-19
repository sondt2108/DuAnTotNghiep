package com.example.datn.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.datn.models.Category;
import com.example.datn.models.Product;
import com.example.datn.repository.CategoryRepository;
import com.example.datn.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {


    @Autowired
    CartService cartService;



	@GetMapping("/cart")
	public ResponseEntity<Map<String, Object>> getAllCart() {
        Map<String, Object> result = new HashMap<>();
		result.put("cart", cartService.getCart());

		Map<Product, Integer> listItems = cartService.getCart().getCartDetails();


        listItems.keySet().stream().mapToInt(listItems::get).forEachOrdered(quantity -> result.put("quantity", quantity));

		return new ResponseEntity<>(result, HttpStatus.OK);

	}
//
//    @GetMapping("cart/them-vao-gio/{idSanPham}")
//	public String addGioHang(@PathVariable("idSanPham") int idSanPham) {
//        cartService.themSanPham(idSanPham);
//		return "redirect:/cart/gio-hang";
//	}
//
//	@GetMapping("cart/addCartByInput/{productId}/{quantity}")
//	public String addCartInput(@PathVariable("productId") int productId, @PathVariable("quantity") int quantity) {
//        cartService.onchangeInput(productId, quantity);
//		return "redirect:/cart/gio-hang";
//	}
//
//
//	@GetMapping("cart/tru-san-pham/{idSanPham}")
//	public String truSanPham(@PathVariable("idSanPham") int idSanPham) {
//        cartService.truSanPham(idSanPham);
//		return "redirect:/cart/gio-hang";
//	}
//
//	@GetMapping("cart/xoa-san-pham/{idSanPham}")
//	public String xoaSanPham(@PathVariable("idSanPham") int idSanPham) {
//        cartService.xoaSanPham(idSanPham);
//		return "redirect:/cart/gio-hang";
//	}
//
//	@Autowired
//	CategoryRepository categoryRepository;
//
//
//
//	@GetMapping("cart")
//	public String  cart(Model model) {
//		model.addAttribute("cart", cartService.getGioHang());
//		model.addAttribute("total", cartService.getTotal().toString());
//
//		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();
//
//
//	for (Product product : listItems.keySet()) {
//
//
//
//
//		int quantity = listItems.get(product);
//
//		model.addAttribute("quantity", quantity);
//
//		System.out.println("sonne");
//		System.out.println(quantity);
//
//	}
//
//	List<Category> categories = categoryRepository.findAll();
//		model.addAttribute("cate", categories);
//
//		return "giohang";
//
//	}
//
//
//	@GetMapping("cart/addCartByInputOnchange/{productId}/{quantity}")
//	public String addCartInputOnchange(@PathVariable("productId") int productId, @PathVariable("quantity") int quantity) {
//        cartService.onchangeInput(productId, quantity);
//		return "redirect:/cart";
//	}
//
//	@GetMapping("cart/add/{idSanPham}")
//	public String addCart(@PathVariable("idSanPham") int idSanPham) {
//        cartService.themSanPham(idSanPham);
//		return "redirect:/cart";
//	}
//
//
//	@GetMapping("cart/minus/{idSanPham}")
//	public String minusCart(@PathVariable("idSanPham") int idSanPham) {
//        cartService.truSanPham(idSanPham);
//		return "redirect:/cart";
//	}
//
//	@GetMapping("cart/remove/{idSanPham}")
//	public String removeCart(@PathVariable("idSanPham") int idSanPham) {
//        cartService.xoaSanPham(idSanPham);
//		return "redirect:/cart";
//	}
}
