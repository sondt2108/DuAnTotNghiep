package com.example.shop.controller;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import com.example.shop.payload.request.ForgotPasswordRequest;
import com.example.shop.payload.request.ResetPassword;
import com.example.shop.service.impl.MailServiceImpl;
import com.example.shop.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.example.shop.payload.response.MessageResponse;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MailServiceImpl mailService;
  
  
    @PostMapping("/account/forgot-password")
      public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest request) {

        String email = forgotPasswordRequest.getEmail();
  
          String response = userService.forgotPassword(email);
  
          if (!response.startsWith("Invalid")) {

            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
              response = url + "/account/reset-password?token=" + response; 

              //mailService.sendMailWithResetPassword(email, response);

              ForgotPasswordRequest fPasswordRequest = new ForgotPasswordRequest();
              fPasswordRequest.setEmail(forgotPasswordRequest.getEmail());
              fPasswordRequest.setReponse(response);
              fPasswordRequest.setSubject("Reset password on Duc Phat");

              mailService.sendMailResetPassword(fPasswordRequest);
          }else {
            return ResponseEntity.ok(new MessageResponse("Email is not registered, please enter another email!"));
          }     
          return ResponseEntity.ok(new MessageResponse("We have sent a password reset request to your email, please check your email!"));
      }


      
  
      @PutMapping("/account/reset-password")
      public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPassword resetPassword
      ) {

                String password = resetPassword.getPassword();

                String token = resetPassword.getToken();

                userService.resetPassword(token, password);
  
                return ResponseEntity.ok(new MessageResponse("Change password successfully!"));
      }


      
    
}
