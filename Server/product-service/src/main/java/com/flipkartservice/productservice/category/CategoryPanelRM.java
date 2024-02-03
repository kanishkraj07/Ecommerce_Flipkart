package com.flipkartservice.productservice.category;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CategoryPanelRM implements RowMapper<CategoryPanel> {
    @Override
    public CategoryPanel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CategoryPanel(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getBoolean(3));
    }
}
