package com.flipkartserver.accountservice.customer;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@JsonDeserialize(builder = CustomerAccountDetails.Builder.class)
public class CustomerAccountDetails {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String gender;
    private String emailId;
    private long mobileNo;
    private String address;
    private Integer pincode;
    private Instant registeredTimestamp;

    public CustomerAccountDetails(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.emailId = builder.emailId;
        this.mobileNo = builder.mobileNo;
        this.address = builder.address;
        this.pincode = builder.pincode;
        this.registeredTimestamp = builder.registeredTimestamp;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmailId() {
        return emailId;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPincode() {
        return pincode;
    }

    public Instant getRegisteredTimestamp() {
        return registeredTimestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CustomerAccountDetails customerAccountDetails) {
        return new Builder(customerAccountDetails);
    }

    public static class Builder {

        private UUID customerId;
        private String firstName;
        private String lastName;
        private String gender;
        private String emailId;
        private long mobileNo;
        private String address;
        private Integer pincode;
        private Instant registeredTimestamp;


        public Builder() {}

        public Builder(CustomerAccountDetails customerAccountDetails) {
            this.customerId = customerAccountDetails.getCustomerId();
            this.firstName = customerAccountDetails.getFirstName();
            this.lastName = customerAccountDetails.getLastName();
            this.gender = customerAccountDetails.getGender();
            this.emailId = customerAccountDetails.getEmailId();
            this.mobileNo = customerAccountDetails.getMobileNo();
            this.address = customerAccountDetails.getAddress();
            this.pincode = customerAccountDetails.getPincode();
            this.registeredTimestamp = customerAccountDetails.getRegisteredTimestamp();
        }

        public Builder setCustomerId(UUID customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public Builder setMobileNo(long mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPincode(Integer pincode) {
            this.pincode = pincode;
            return this;
        }

        public Builder setRegisteredTimestamp(Instant registeredTimestamp) {
            this.registeredTimestamp = registeredTimestamp;
            return this;
        }

        public CustomerAccountDetails build() {
            return new CustomerAccountDetails(this);
        }
    }
}
