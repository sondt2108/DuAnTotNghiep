package com.example.datn.repository;

import java.util.Optional;

import com.example.datn.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    User findByEmail(String email);

    User findByToken(String token);
  
    Boolean existsByUsername(String username);
  
    Boolean existsByEmail(String email);


    @Query(value = "select user_id from user_roles where  role_id = 1 and user_id = ?1", nativeQuery = true)
    Optional<User> findByRole(@Param(value = "user_id") Long userId);
  }
