package com.example.shop.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name="orders")
public class Order {

    @Id
    private Long orderId;


    @NotNull
	private Date createdDate = new Date((new java.util.Date()).getTime());


    
	@Size(max = 50)
	private String province;


    
	@Size(max = 50)
	private String district;

    
	@Size(max = 50)
	private String ward;

    
	@Size(max = 150)
	private String address;

    
	@Size(max = 10)
	private String phoneNumber;

    
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal total;


	private String email;


	private String fullName;


	private String note;
    

    @ManyToOne
	@JoinColumn(name = "customerId", nullable = true, foreignKey = @ForeignKey(name = "customer_order"))
	Customer customer;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderItems;

	@ManyToOne
	@JoinColumn(
			name = "orderStatusId",
			nullable = true,
			foreignKey = @ForeignKey(name = "orderStatus_order"))
	private OrderStatus orderStatus;

	public Order() {
	}

	public Order(Long orderId, Date createdDate, String province, String district, String ward, String address, String phoneNumber, BigDecimal total, String email, String fullName, String note, Customer customer, List<OrderDetail> orderDetails, OrderStatus orderStatus) {
		this.orderId = orderId;
		this.createdDate = createdDate;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.total = total;
		this.email = email;
		this.fullName = fullName;
		this.note = note;
		this.customer = customer;
		this.orderItems = orderDetails;
		this.orderStatus = orderStatus;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderDetail> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
