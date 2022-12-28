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


@Entity(name = "trademark")
public class Trademark {

    @Id
	@GeneratedValue
	private int trademarkId;
	@NotNull
	@Size(min = 3, max = 50)
	private String trademarkName;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "trademark")
	private List<Product> products;

	
	
	public Trademark() {
	}

	public Trademark(int trademarkId, String trademarkName, List<Product> products) {
		this.trademarkId = trademarkId;
		this.trademarkName = trademarkName;
		this.products = products;
	}

	public int getTrademarkId() {
		return trademarkId;
	}

	public void setTrademarkId(int trademarkId) {
		this.trademarkId = trademarkId;
	}

	public String getTrademarkName() {
		return trademarkName;
	}

	public void setTrademarkName(String trademarkName) {
		this.trademarkName = trademarkName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	


	

	
    
}
