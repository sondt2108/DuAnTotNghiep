package com.example.shop.models;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.shop.payload.request.CategoryRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="categories")
public class Category {
    @Id
	@GeneratedValue
	private int categoryId;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "category")
	private List<Product> products;

	private String icon;

	private String seoUrl;

	public Category() {
		super();
	}

	public Category(int categoryId, @NotNull @Size(min = 3, max = 50) String name, List<Product> products) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.products = products;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSeoUrl() {
		return seoUrl;
	}

	public void setSeoUrl(String seoUrl) {
		this.seoUrl = seoUrl;
	}

	public static Category toCategory(CategoryRequest categoryRequest){
		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setSeoUrl(categoryRequest.getSeoUrl());
		category.setIcon(categoryRequest.getIcon());
		return category;
	}

	

}
