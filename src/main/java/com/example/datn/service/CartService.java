package com.example.datn.service;


import java.math.BigDecimal;

import com.example.datn.models.Cart;


public interface CartService {
    public Cart getCart();
	public void addProduct(int productId);
	public void minusProduct(int productId);
	public void removeProduct(int productId);
	public void onchangeInput(int productId, int quantity);
	


	
}
