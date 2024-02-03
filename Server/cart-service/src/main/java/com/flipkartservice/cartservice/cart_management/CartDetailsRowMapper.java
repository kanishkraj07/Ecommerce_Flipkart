package com.flipkartservice.cartservice.cart_management;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CartDetailsRowMapper implements RowMapper<CartDetails> {
    @Override
    public CartDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CartDetails(
                UUID.fromString(rs.getString("product_id")),
                rs.getInt("quantity"), UUID.fromString(rs.getString("cart_id")));
    }
}
