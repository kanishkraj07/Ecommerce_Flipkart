package com.flipkartserver.authenticationservice.OtpAuthentication;

import java.util.UUID;

public class CustomerDetailsRequest {
    private UUID customerId;
    private long mobileNo;

    public CustomerDetailsRequest() {
    }

    public CustomerDetailsRequest(UUID customerId, long mobileNo) {
        this.customerId = customerId;
        this.mobileNo = mobileNo;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public CustomerDetailsRequest setCustomerId(UUID customerId) {
        this.customerId = customerId;
        return this;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public CustomerDetailsRequest setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }
}
