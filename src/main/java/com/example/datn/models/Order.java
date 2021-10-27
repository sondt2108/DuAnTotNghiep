package com.example.datn.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name="orders")
public class Order {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;


    @NotNull
	private Date createdDate = new Date((new java.util.Date()).getTime());


    @NotBlank
	@Size(max = 50)
	private String province;


    @NotBlank
	@Size(max = 50)
	private String district;

    @NotBlank
	@Size(max = 50)
	private String ward;

    @NotBlank
	@Size(max = 150)
	private String address;

    @NotBlank
	@Size(max = 10)
	private String phoneNumber;

    
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal total;


	private String note;
    

    @ManyToOne
	@JoinColumn(name = "customer_Id", nullable = false, foreignKey = @ForeignKey(name = "customer_order"))
	Customer customer;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> order_items;


	
    
}
