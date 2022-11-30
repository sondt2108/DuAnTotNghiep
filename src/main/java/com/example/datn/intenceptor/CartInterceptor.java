package com.example.datn.intenceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.datn.models.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CartInterceptor implements HandlerInterceptor {
	

	@Autowired
	private Cart cart;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object handler)
	throws Exception {
		// nếu giỏ hàng trống thì chuyển về trang chủ
		if (cart.getCartDetails().size() == 0) {
			
			response.sendRedirect("/");
			return false;
		}
		return true;
	}
}
