package com.dec2022.restapis.model;

public class AddProductRequest {

    private String productName;
    private Float price;

    public AddProductRequest() {
    }

    public AddProductRequest(String productName, Float price) {
        this.productName = productName;
        this.price = price;
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
