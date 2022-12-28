package com.example.shop.service.impl;

import com.example.shop.exception.BadRequestException;
import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.models.Cart;
import com.example.shop.models.Product;

import com.example.shop.service.CartService;
import com.example.shop.service.ProductService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	private static final String PRODUCT_NOT_FOUND = "Product not found!";
	private static final String REQUIRE_FIELD_MISSING = "Require field missing";
	
	@Autowired
	private Cart cart;

	@Autowired
	ProductService productService;
	
	public Cart getCart() {
		return cart;
	}

	@Override
	public void addProduct(int productId) {
		Product product = validateProduct(productId);
		if (cart.getCartDetails().containsKey(product)) {
			int count = cart.getCartDetails().get(product);
			cart.getCartDetails().replace(product, count + 1);
		} else {
			cart.getCartDetails().put(product, 1);
		}
	}

	@Override
	public void onchangeInput(int productId, int quantity) {
		Product product = validateProduct(productId);
		if (StringUtils.isBlank(Integer.toString(quantity))){
			throw new BadRequestException(REQUIRE_FIELD_MISSING + " quantity");
		}
		if (cart.getCartDetails().containsKey(product)) {
			cart.getCartDetails().replace(product, quantity);
		} else {
			cart.getCartDetails().put(product, 1);
		}
	}
	
	
	@Override
	public void minusProduct(int productId) {
		Product product = validateProduct(productId);
		if (cart.getCartDetails().containsKey(product)) {
			int count = cart.getCartDetails().get(product);
			cart.getCartDetails().replace(product, count - 1);
		}
	}
	
	@Override
	public void removeProduct(int productId) {
		Product product = validateProduct(productId);
		cart.getCartDetails().remove(product);
	}

	public Product validateProduct(int productId){
		if (StringUtils.isBlank(Integer.toString(productId))){
			throw new BadRequestException(REQUIRE_FIELD_MISSING + " productId");
		}
		Product product = productService.getProductById(productId);
		if (ObjectUtils.isEmpty(product)){
			throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
		}
		return product;
	}

	
}