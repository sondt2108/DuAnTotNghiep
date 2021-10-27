package com.example.datn.controller;

import com.example.datn.payload.response.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OderRescontroller {
    

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(){

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
