package com.example.shop.repository;

import com.example.shop.models.MessageNotifications;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageNotificationsRepository extends JpaRepository<MessageNotifications, Long> {
    
}
