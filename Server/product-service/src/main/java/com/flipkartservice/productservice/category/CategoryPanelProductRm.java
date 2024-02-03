package com.flipkartservice.productservice.category;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CategoryPanelProductRm implements RowMapper<CategoryPanelProduct> {

    @Override
    public CategoryPanelProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CategoryPanelProduct(UUID.fromString(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
    }

//    @Override
//    public CategoryPanelWIthProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new CategoryPanelWIthProductModel(UUID.fromString(rs.getString("panel_id")), rs.getString("panel_name"), UUID.fromString(rs.getString("product_id")), rs.getString("product_img"), rs.getString("product_name"), rs.getInt("product_price"), rs.getInt("min_offer"), rs.getInt("max_offer"));
//    }
}
