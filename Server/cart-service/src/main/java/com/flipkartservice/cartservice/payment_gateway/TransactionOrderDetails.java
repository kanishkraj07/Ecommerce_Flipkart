package com.flipkartservice.cartservice.payment_gateway;

public class TransactionOrderDetails {
    private String orderId;
    private String transactionKey;
    private String transactionSecret;
    private Integer amount;
    private String currency;

    public TransactionOrderDetails(String orderId, String transactionKey, String transactionSecret, Integer amount, String currency) {
        this.orderId = orderId;
        this.transactionKey = transactionKey;
        this.transactionSecret = transactionSecret;
        this.amount = amount;
        this.currency = currency;
    }

    public TransactionOrderDetails setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public TransactionOrderDetails setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
        return this;
    }

    public TransactionOrderDetails setTransactionSecret(String transactionSecret) {
        this.transactionSecret = transactionSecret;
        return this;
    }

    public TransactionOrderDetails setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public TransactionOrderDetails setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public String getTransactionSecret() {
        return transactionSecret;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
