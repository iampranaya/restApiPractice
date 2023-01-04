package com.dec2022.restapis.model;

public class PutProductRequest {

    private String productId;
    private String productName;
    private Float price;

    public PutProductRequest() {
    }

    public PutProductRequest(String productId, String productName, Float price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
