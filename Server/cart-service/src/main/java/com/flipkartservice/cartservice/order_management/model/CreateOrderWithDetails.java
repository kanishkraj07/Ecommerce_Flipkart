package com.flipkartservice.cartservice.order_management.model;

import com.flipkartservice.cartservice.order_management.status_enum.PaymentStatus;

import java.util.List;
import java.util.UUID;

public class CreateOrderWithDetails {
    private List<UUID> cartIds;
    private UUID userId;
    private String transactionId;
    private PaymentStatus paymentStatus;

    public CreateOrderWithDetails() {
    }

    public CreateOrderWithDetails(List<UUID> cartIds, UUID userId, String transactionId, PaymentStatus paymentStatus) {
        this.cartIds = cartIds;
        this.userId = userId;
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
    }

    public List<UUID> getCartIds() {
        return cartIds;
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

