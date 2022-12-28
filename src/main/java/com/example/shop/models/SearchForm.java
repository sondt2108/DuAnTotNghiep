package com.example.shop.models;

public class SearchForm {
    private String name;
	private int page;
	private String orderBy;
	private boolean orderly;
	
	public SearchForm(String name, int page, String orderBy, boolean orderly) {
		super();
		this.name = name;
		this.page = page;
		this.orderBy = orderBy;
		this.orderly = orderly;
	}
	
	public SearchForm() {
		this.name = "";
		this.page = 0;
		this.orderBy = "productId";
		this.orderly = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isOrderly() {
		return orderly;
	}

	public void setOrderly(boolean orderly) {
		this.orderly = orderly;
	}
}
