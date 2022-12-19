package com.example.datn.service.impl;

import com.example.datn.models.Role;
import com.example.datn.models.User;
import com.example.datn.payload.request.SignupRequest;
import com.example.datn.repository.UserRepository;

import java.time.*;
import java.util.*;

import com.example.datn.service.CustomerService;
import com.example.datn.service.UserService;
import com.example.datn.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

	@Autowired
	UserSession userSession;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CustomerService customerService;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
     }

	 @Override
	public String forgotPassword(String email) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepository.findByEmail(email));

		if (userOptional.isEmpty()) {
			return "Invalid email id.";
		}

		User user = userOptional.get();
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());

		user = userRepository.save(user);

		return user.getToken();
	}

	@Override
	public boolean getUser(Long userId) {
		User user = userRepository.findByRole(userId);
		if(user != null) {
			userSession.setUser(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean isRole() {
		return userSession.getUser() != null;
	}

	@Override
	public String checkToken(String token) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepository.findByToken(token));

		if (userOptional.isEmpty()) {
			return "Invalid token.";
		}

		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return "Invalid Token expired.";

		}

		return "ok";
	}

	@Override
	public void logoutAdmin(Long userId) {
		userSession.setUser(null);
	}


	public void resetPassword(String token, String password) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepository.findByToken(token));

		if (userOptional.isEmpty()) {
			return;
		}

		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return;

		}

		User user = userOptional.get();

		user.setPassword(encoder.encode(password));
		user.setToken(null);
		user.setTokenCreationDate(null);

		userRepository.save(user);

	}

	@Override
	public boolean isExistsByUsername(String userName) {
		return false;
	}

	@Override
	public boolean isExistsEmail(String email) {
		return false;
	}

	@Override
	public User create(SignupRequest signupRequest, Set<Role> roles) {
		User user = new User();
		user.setUsername(signupRequest.getUsername());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(encoder.encode(signupRequest.getPassword()));

		//create customer inf
		customerService.createCustomer(user, signupRequest);
		return userRepository.save(user);
	}

	/**
	 * Generate unique token. You may add multiple parameters to create a strong
	 * token.
	 *
	 * @return unique token
	 */
	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}

	/**
	 * Check whether the created token expired or not.
	 *
	 * @param tokenCreationDate
	 * @return true or false
	 */
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}

}
