package com.example.shop.payload.request;

public class CartRequest {
    private String productId;

    private int quantity;

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public CartRequest() {
    }

    public CartRequest(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
