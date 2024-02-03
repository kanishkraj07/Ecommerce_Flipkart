package com.flipkartservice.productservice.category;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoryPanelProduct {

    private UUID productId;
    private String productImg;
    private String productName;
    private Integer productPrice;
    private Integer minOffer;
    private Integer maxOffer;

    public CategoryPanelProduct() {
    }

    public CategoryPanelProduct(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.productImg  = builder.productImg;
        this.productPrice = builder.productPrice;
        this.minOffer = builder.minOffer;
        this.maxOffer = builder.maxOffer;
    }

    public CategoryPanelProduct(UUID productId, String productImg, String productName, Integer productPrice, Integer minOffer, Integer maxOffer) {
        this.productId = productId;
        this.productImg = productImg;
        this.productName = productName;
        this.productPrice = productPrice;
        this.minOffer = minOffer;
        this.maxOffer = maxOffer;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductImg() {
        return productImg;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public Integer getMinOffer() {
        return minOffer;
    }

    public Integer getMaxOffer() {
        return maxOffer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID productId;
        private String productImg;
        private String productName;
        private Integer productPrice;
        private Integer minOffer;
        private Integer maxOffer;

        public Builder setProductId(UUID productId) {
            this.productId = productId;
            return this;
        }

        public Builder setProductImg(String productImg) {
            this.productImg = productImg;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setProductPrice(Integer productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public Builder setMinOffer(Integer minOffer) {
            this.minOffer = minOffer;
            return this;
        }

        public Builder setMaxOffer(Integer maxOffer) {
            this.maxOffer = maxOffer;
            return this;
        }

        public CategoryPanelProduct build() {
            return new CategoryPanelProduct(this);
        }
    }
}
