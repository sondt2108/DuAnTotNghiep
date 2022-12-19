package com.example.datn.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.datn.exception.TokenRefreshException;
import com.example.datn.models.Customer;
import com.example.datn.models.ERole;
import com.example.datn.models.RefreshToken;
import com.example.datn.models.Role;
import com.example.datn.models.User;
import com.example.datn.payload.request.LogOutRequest;
import com.example.datn.payload.request.LoginRequest;
import com.example.datn.payload.request.SignupRequest;
import com.example.datn.payload.request.TokenRefreshRequest;
import com.example.datn.payload.response.JwtResponse;
import com.example.datn.payload.response.MessageResponse;
import com.example.datn.payload.response.TokenRefreshResponse;
import com.example.datn.repository.CustomerRepository;
import com.example.datn.repository.RoleRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.security.jwt.JwtUtils;
import com.example.datn.security.services.RefreshTokenService;
import com.example.datn.security.services.UserDetailsImpl;
import com.example.datn.service.CustomerService;
import com.example.datn.service.UserService;
import com.example.datn.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  RoleRepository roleRepository; 

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @Autowired
  CustomerService customerService;


  @Autowired
  UserService userService;

  private static final String ROLE_NOT_FOUND = "Role Not found!";
  
  @PostMapping(value = "/login", produces = "application/json")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
   
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    userService.getUser(userDetails.getId());

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
        userDetails.getUsername(), userDetails.getEmail(), roles));

  }
  

  @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.isExistsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse(String.format("Error: Username %s already exists!", signUpRequest.getUsername())));
        }

        if (userService.isExistsEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse(String.format("Error: Email %s already exists", signUpRequest.getEmail())));
        }

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("mod".equals(role)) {
                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                    roles.add(modRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
                    roles.add(userRole);
                }
            });
        }
        userService.create(signUpRequest, roles);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
          
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() ->  new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
  }

  

  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {

    Long id = logOutRequest.getUserId();
    refreshTokenService.deleteByUserId(id);
		customerService.logout(logOutRequest.getUserId());
		//remove sess
    userService.logoutAdmin(logOutRequest.getUserId());
		
    return ResponseEntity.ok(new MessageResponse("Log out successfully!"));
  }


  

}
