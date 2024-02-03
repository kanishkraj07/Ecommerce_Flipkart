package com.flipkartservice.cartservice.order_management.status_enum;

public enum PaymentStatus {
    SUCCESS("Success"),
    FAILED("Failed");

    private String status;
    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
