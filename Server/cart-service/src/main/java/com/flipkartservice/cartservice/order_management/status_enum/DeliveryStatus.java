package com.flipkartservice.cartservice.order_management.status_enum;

public enum DeliveryStatus {
    SHIPPED("Shipped"),
    OUT_FOR_DELIVERY("Out For Delivery"),
    DELIVERED("Delivered");

    private String status;
    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
