package com.flipkartserver.accountservice.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public CustomerAccountDetails getAccountByMobileNo(long mobileNo) {
        try{
            return jdbcTemplate.queryForObject("Select * from customer_by_customer_id where mobile_no=?", new CustomerAccountRowMapper(), mobileNo);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void registerCustomer(CustomerAccountDetails customerAccountDetails) {
        String query = "INSERT into customer_by_customer_id(customer_id, mobile_no, registeredTimestamp) VALUES(?,?,?)";
        jdbcTemplate.update(query, customerAccountDetails.getCustomerId().toString(),customerAccountDetails.getMobileNo(), customerAccountDetails.getRegisteredTimestamp());
    }
}


