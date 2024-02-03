package com.flipkartserver.accountservice.customer;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

public class CustomerAccountRowMapper implements RowMapper<CustomerAccountDetails> {
    @Override
    public CustomerAccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CustomerAccountDetails.builder()
                .setCustomerId(UUID.fromString(rs.getString("customer_id")))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .setGender(rs.getString("gender"))
                .setEmailId(rs.getString("email"))
                .setMobileNo(rs.getLong("mobile_no"))
                .setAddress(rs.getString("address"))
                .setPincode(rs.getInt("pincode"))
                .setRegisteredTimestamp(rs.getTimestamp("registeredTimestamp").toInstant())
                .build();
    }
}
