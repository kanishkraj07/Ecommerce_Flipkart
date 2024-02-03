package com.flipkartservice.productservice.category;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(rs.getString(1), rs.getString(2), rs.getString(3));
    }
}
