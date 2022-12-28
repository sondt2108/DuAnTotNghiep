package com.example.shop.controller;


import java.util.HashMap;
import java.util.Map;

import com.example.shop.models.Product;
import com.example.shop.payload.request.CartRequest;
import com.example.shop.service.CartService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("cart/add")
	public ResponseEntity<Void> addCart(@RequestBody CartRequest cartRequest) {
        cartService.addProduct(Integer.parseInt(cartRequest.getProductId()));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("cart/onchangeInput")
	public ResponseEntity<Void> addCartByOnchangeInput(@RequestBody CartRequest cartRequest) {
        cartService.onchangeInput(Integer.parseInt(cartRequest.getProductId()), cartRequest.getQuantity());
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@PostMapping("cart/remove}")
	public ResponseEntity<Void> removeProduct(@RequestBody CartRequest cartRequest) {
        cartService.removeProduct(Integer.parseInt(cartRequest.getProductId()));
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
