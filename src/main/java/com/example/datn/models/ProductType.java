package com.example.datn.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "productType")
public class ProductType {

    @Id
	@GeneratedValue
	private int productTypeId;
	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "productType")
	private List<Product> products;

	public ProductType() {
	}

	public ProductType(int productTypeId, String name, List<Product> products) {
		this.productTypeId = productTypeId;
		this.name = name;
		this.products = products;
	}

	public int getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	
	
	


	

	
    
}
