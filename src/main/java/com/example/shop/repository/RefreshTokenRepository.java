package com.example.shop.repository;


import java.util.Optional;

import com.example.shop.models.RefreshToken;
import com.example.shop.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
  
    @Modifying
    int deleteByUser(User user);
  }
