package com.example.datn.service.impl;

import com.example.datn.models.Cart;
import com.example.datn.models.Product;

import com.example.datn.service.CartService;
import com.example.datn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	
	
	@Autowired
	private Cart cart;

	@Autowired
	ProductService productService;
	
	public Cart getCart() {
		return cart;
	}

	@Override
	public void addProduct(int productId) {
		Product product = productService.getProductById(productId);
		if (cart.getCartDetails().containsKey(product)) {
			int count = cart.getCartDetails().get(product);
			cart.getCartDetails().replace(product, count + 1);
		} else {
			cart.getCartDetails().put(product, 1);
		}
	}

	@Override
	public void onchangeInput(int productId, int quantity) {
		Product product = productService.getProductById(productId);
		if (cart.getCartDetails().containsKey(product)) {
			cart.getCartDetails().replace(product, quantity);
		} else {
			cart.getCartDetails().put(product, 1);
		}
	}
	
	
	@Override
	public void minusProduct(int productId) {
		Product product = productService.getProductById(productId);
		if (cart.getCartDetails().containsKey(product)) {
			int count = cart.getCartDetails().get(product);
			cart.getCartDetails().replace(product, count - 1);
		}
	}
	
	@Override
	public void removeProduct(int productId) {
		Product product = productService.getProductById(productId);
		cart.getCartDetails().remove(product);
	}

	
}