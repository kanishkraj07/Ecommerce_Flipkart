package com.flipkartservice.cartservice.cart_management;

import java.util.UUID;

public class CartDetails {
    private UUID productId;
    private Integer quantity;
    private UUID cartId;

    public CartDetails() {
    }

    public CartDetails(Builder builder) {
        this.productId = builder.productId;
        this.quantity = builder.quantity;
        this.cartId = builder.cartId;
    }

    public CartDetails(UUID productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartDetails(UUID productId, Integer quantity, UUID cartId) {
        this.productId = productId;
        this.quantity = quantity;
        this.cartId = cartId;
    }

    public static Builder builder() {
        return new Builder();
    }
    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public UUID getCartId() {
        return cartId;
    }


    public CartDetails setProductId(UUID productId) {
        this.productId = productId;
        return this;
    }

    public CartDetails setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CartDetails setCartId(UUID cartId) {
        this.cartId = cartId;
        return this;
    }

    public static class Builder {
        private UUID productId;
        private Integer quantity;
        private UUID cartId;

        public Builder setProductId(UUID productId) {
            this.productId = productId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setCartId(UUID cartId) {
            this.cartId = cartId;
            return this;
        }

        public CartDetails build() {
            return new CartDetails(this);
        }
    }
}
