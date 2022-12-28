package com.example.shop.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="notification")
public class MessageNotifications {
    
    @Id
    @GeneratedValue
    private Long notificationId;



    @NotNull
	private Date createdDate = new Date((new java.util.Date()).getTime());

    @NotNull
    private Long orderId;


    private String description;

    public MessageNotifications() {
    }

    public MessageNotifications(Long notificationId, @NotNull Date createdDate, @NotNull Long orderId) {
        this.notificationId = notificationId;
        this.createdDate = createdDate;
        this.orderId = orderId;
    }

    public Long getNotificationsId() {
        return notificationId;
    }

    public void setNotificationsId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    
    
}
