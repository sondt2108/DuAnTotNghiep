package com.example.datn.models;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "orderStatus")
public class OrderStatus {
    @Id
	private int orderStatusId;
	
	private String status;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "OrderStatus")
	private List<Order> orders;

    public OrderStatus() {
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

	

}