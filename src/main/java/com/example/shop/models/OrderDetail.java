package com.example.shop.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="orderDetail")
public class OrderDetail {


    @Id
	@GeneratedValue
    private Long orderDetailId;


    @NotBlank
    private String productName;


    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private double price;

    @NotNull
    private int quantity;

    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "orderId", nullable = false, foreignKey = @ForeignKey(name = "order_orderItems"))
	Order order;
	
	@ManyToOne
	@JoinColumn(name = "productId", nullable = false, foreignKey = @ForeignKey(name = "product_orderDetail"))
	Product product;

    private double total;

    public OrderDetail() {
    }

    

    public OrderDetail(Long orderDetailId, @NotBlank String productName,
            @DecimalMin(value = "0.00", message = "*Price has to be non negative number") double price,
            @NotBlank int quantity, Order order, Product product) {
        this.orderDetailId = orderDetailId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }



    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
