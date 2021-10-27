package com.example.datn.models;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;


@Entity(name="orderDetail")
public class OrderDetail {


    @Id
	@GeneratedValue
    private Long orderDetailId;


    @NotBlank
    private String productName;


    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal price;

    @NotBlank
    private int quantity;


    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal total;


    @ManyToOne
	@JoinColumn(name = "orderId", nullable = false, foreignKey = @ForeignKey(name = "order_orderitems"))
	Order order;
	
	@ManyToOne
	@JoinColumn(name = "productId", nullable = false, foreignKey = @ForeignKey(name = "product_orderDetail"))
	Product product;
    
}
