package com.example.datn.service;


import java.math.BigDecimal;

import com.example.datn.models.Cart;


public interface CartService {
	Cart getCart();
	void addProduct(int productId);
	void minusProduct(int productId);
	void removeProduct(int productId);
	void onchangeInput(int productId, int quantity);
}
