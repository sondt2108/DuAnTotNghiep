package com.example.shop.service;

import com.example.shop.models.Role;
import com.example.shop.models.User;
import com.example.shop.payload.request.SignupRequest;

import java.util.Set;

public interface UserService {
    String forgotPassword(String email);
    boolean getUser(Long userId);
    boolean isExistsEmail(String email);
    boolean isExistsByUsername(String userName);
    boolean isRole();
    String checkToken(String token);
    void logoutAdmin(Long userId);
    void resetPassword(String token, String password);
    User create(SignupRequest signupRequest, Set<Role> roles);

}
