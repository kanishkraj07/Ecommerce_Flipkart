package com.flipkartserver.accountservice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    public boolean isAccountRegistered(long mobileNo) {
        return Objects.nonNull(customerDao.getAccountByMobileNo(mobileNo));
    }

    public CustomerAccountDetails registerAccount(UUID customerId, long mobileNo) {
        CustomerAccountDetails updatedAccountCustomerDetails = CustomerAccountDetails.builder()
                .setCustomerId(customerId)
                .setMobileNo(mobileNo)
                .setRegisteredTimestamp(Instant.now())
                .build();
        customerDao.registerCustomer(updatedAccountCustomerDetails);
        return updatedAccountCustomerDetails;
    }

    public UUID getAccountId(long mobileNo) {
        CustomerAccountDetails customerAccountDetails = customerDao.getAccountByMobileNo(mobileNo);
        return Objects.nonNull(customerAccountDetails) ? customerAccountDetails.getCustomerId() : null;
    }
}
