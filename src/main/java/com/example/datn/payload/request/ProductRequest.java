package com.example.datn.payload.request;

import com.example.datn.models.*;

import java.util.ArrayList;
import java.util.List;

public class ProductRequest {
    private int productId;

    private String productName;

    private Supplier supplier;

    private Trademark trademark;

    private ProductType productType;

    private int quantity;

    private int promotion;

    private String productStatus;

    private double price;

    private Category category;

    private String productImg;

    private String seoUrl;

    private String productDetail;


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

    public static ProductRequest toProductRequest(Product product){

        return productToProductRequest(product);
    }

    public static  List<ProductRequest> toProductRequestList(List<Product> products){
        List<ProductRequest> productRequestList = new ArrayList<>();
        for (Product product : products){
            productRequestList.add(productToProductRequest(product));
        }
        return productRequestList;
    }

    public static ProductRequest productToProductRequest(Product product){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName(product.getProductName());
        productRequest.setProductImg(product.getProductImg());
        productRequest.setProductDetail(product.getProductDetail());
        productRequest.setPrice(product.getPrice());
        productRequest.setSeoUrl(product.getSeoUrl());
        productRequest.setCategory(product.getCategory());
        productRequest.setProductStatus(product.getProductStatus());
        productRequest.setQuantity(product.getQuantity());
        productRequest.setTrademark(product.getTrademark());
        productRequest.setSupplier(product.getSupplier());
        productRequest.setPromotion(product.getPromotion());
        productRequest.setProductType(product.getProductType());

        return productRequest;
    }
}
