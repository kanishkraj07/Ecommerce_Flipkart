package com.flipkartservice.cartservice.cart_management;

import java.util.UUID;

public class CartUser {
    private UUID userId;

    public CartUser() {
    }

    public CartUser(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public CartUser setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }
}
