package com.example.datn.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.datn.payload.request.ProductRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity(name="products")
public class Product {
    
    @Id
	@GeneratedValue
	private int productId;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String productName;
	
	
	@ManyToOne
	@JoinColumn(
			name = "supplierId",
			nullable = true,
			foreignKey = @ForeignKey(name = "supplier_product"))
	private Supplier supplier;
	
	
	
	
	@ManyToOne
	@JoinColumn(
			name = "trademarkId",
			nullable = true,
			foreignKey = @ForeignKey(name = "trademark_product"))
	private Trademark trademark;


	@ManyToOne
	@JoinColumn(
			name = "productTypeId",
			nullable = true,
			foreignKey = @ForeignKey(name = "productType_product"))
	private ProductType productType;


	private int quantity;
	
	private int promotion;
	
	private String productStatus;

	@Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private double price;

	@NotNull
	private Date createdDate = new Date((new java.util.Date()).getTime());

	@ManyToOne
	@JoinColumn(
			name = "categoryId",
			nullable = true,
			foreignKey = @ForeignKey(name = "category_products"))
	private Category category;


	private String productImg;


	private String seoUrl;

	@Size(min = 0, max = 1000)
	private String productDetail;


	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "product")
	private List<ImgProduct> imgProduct;


	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "product")
	private List<OrderDetail> orderDetail;

	@OneToMany(fetch = FetchType.LAZY
			, mappedBy = "product")
	private List<Review> review;

	public Product() {

	}

	public Product(int productId, String productName, Supplier supplier, Trademark trademark, ProductType productType, int quantity, int promotion, String productStatus, double price, Date createdDate, Category category, String productImg, String seoUrl, String productDetail, List<ImgProduct> imgProduct, List<OrderDetail> orderDetail, List<Review> review) {
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.trademark = trademark;
		this.productType = productType;
		this.quantity = quantity;
		this.promotion = promotion;
		this.productStatus = productStatus;
		this.price = price;
		this.createdDate = createdDate;
		this.category = category;
		this.productImg = productImg;
		this.seoUrl = seoUrl;
		this.productDetail = productDetail;
		this.imgProduct = imgProduct;
		this.orderDetail = orderDetail;
		this.review = review;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Trademark getTrademark() {
		return trademark;
	}

	public void setTrademark(Trademark trademark) {
		this.trademark = trademark;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPromotion() {
		return promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getSeoUrl() {
		return seoUrl;
	}

	public void setSeoUrl(String seoUrl) {
		this.seoUrl = seoUrl;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public List<ImgProduct> getImgProduct() {
		return imgProduct;
	}

	public void setImgProduct(List<ImgProduct> imgProduct) {
		this.imgProduct = imgProduct;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product cProduct = (Product)obj;
			return this.productId == cProduct.getProductId();
		}
		return false;
	}
	 
	@Override
	public int hashCode() {
		return productId;
	}

	public static Product toProduct(ProductRequest productRequest){
		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		product.setProductImg(productRequest.getProductImg());
		product.setProductDetail(productRequest.getProductDetail());
		product.setPrice(productRequest.getPrice());
		product.setSeoUrl(productRequest.getSeoUrl());
		product.setCategory(productRequest.getCategory());
		product.setProductStatus(productRequest.getProductStatus());
		product.setQuantity(productRequest.getQuantity());
		product.setTrademark(productRequest.getTrademark());
		product.setSupplier(productRequest.getSupplier());
		product.setPromotion(productRequest.getPromotion());
		product.setProductType(productRequest.getProductType());

		return product;
	}
	

	


}
