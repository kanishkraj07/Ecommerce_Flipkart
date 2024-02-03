package com.flipkartservice.cartservice.cart_management;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CartUserRowMapper implements RowMapper<CartUser> {
    @Override
    public CartUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CartUser(UUID.fromString(rs.getString("user_id")));
    }
}
