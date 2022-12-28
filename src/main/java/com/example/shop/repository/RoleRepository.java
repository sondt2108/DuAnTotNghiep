package com.example.shop.repository;

import java.util.Optional;

import com.example.shop.models.ERole;
import com.example.shop.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
  }
