package com.flipkartservice.cartservice.order_management;

import com.flipkartservice.cartservice.order_management.status_enum.DeliveryStatus;
import com.flipkartservice.cartservice.order_management.status_enum.OrderStatus;
import com.flipkartservice.cartservice.order_management.status_enum.PaymentStatus;

import java.util.Date;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private UUID userId;
    private String transactionId;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private Date expectedDelivery;
    private DeliveryStatus deliveryStatus;

    public Order() {

    }

    public Order(Builder builder) {
        this.orderId = builder.orderId;
        this.transactionId = builder.transactionId;
        this.paymentStatus = builder.paymentStatus;
        this.orderStatus = builder.orderStatus;
        this.expectedDelivery = builder.expectedDelivery;
        this.deliveryStatus = builder.deliveryStatus;
        this.userId = builder.userId;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public UUID getOrderId() {
        return this.orderId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public Date getExpectedDelivery() {
        return this.expectedDelivery;
    }

    public DeliveryStatus getDeliveryStatus() {
        return this.deliveryStatus;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private UUID orderId;
        private UUID userId;
        private String transactionId;
        private PaymentStatus paymentStatus;
        private OrderStatus orderStatus;
        private Date expectedDelivery;
        private DeliveryStatus deliveryStatus;

        public Builder setOrderId(UUID orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder setTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder setPaymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder setExpectedDelivery(Date expectedDelivery) {
            this.expectedDelivery = expectedDelivery;
            return this;
        }

        public Builder setDeliveryStatus(DeliveryStatus deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
