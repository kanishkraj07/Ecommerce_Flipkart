package com.flipkartservice.cartservice.order_management.status_enum;

public enum OrderStatus {
    CONFIRMED("Confirmed"),
    NOT_CONFIRMED("not Confirmed");

    private String status;
    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
