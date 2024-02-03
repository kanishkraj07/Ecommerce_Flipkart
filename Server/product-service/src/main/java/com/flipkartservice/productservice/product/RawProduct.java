package com.flipkartservice.productservice.product;

import java.util.UUID;

public class RawProduct {
    private UUID productId;
    private String ProductName;

    public RawProduct() {
    }

    public RawProduct(UUID productId, String productName) {
        this.productId = productId;
        ProductName = productName;
    }

    public UUID getProductId() {
        return productId;
    }

    public RawProduct setProductId(UUID productId) {
        this.productId = productId;
        return this;
    }

    public String getProductName() {
        return ProductName;
    }

    public RawProduct setProductName(String productName) {
        ProductName = productName;
        return this;
    }
}
