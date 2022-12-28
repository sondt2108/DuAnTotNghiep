package com.example.shop.service;


import com.example.shop.models.Cart;


public interface CartService {
	Cart getCart();
	void addProduct(int productId);
	void minusProduct(int productId);
	void removeProduct(int productId);
	void onchangeInput(int productId, int quantity);
}
