package com.example.shop.service;

import com.example.shop.models.User;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {
    User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
