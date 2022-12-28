package com.example.shop.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="supplier")
public class Supplier {
    @Id
	@GeneratedValue
	private int supplierId;
	@NotNull
	@Size(min = 3, max = 50)
	private String supplierName;
	
	private String address;
	
	private String phoneNumber;
	
	private String email;
	
	private String description;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "supplier")
	private List<Product> product;


	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "supplier")
	private List<ImportBill> importBill;

	public Supplier() {
		super();
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ImportBill> getImportBill() {
		return importBill;
	}

	public void setImportBill(List<ImportBill> importBill) {
		this.importBill = importBill;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
}
