package com.flipkartservice.productservice.category;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CategoryPanelWithProductModelRm implements RowMapper<CategoryPanelWIthProductModel> {

    @Override
    public CategoryPanelWIthProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CategoryPanelWIthProductModel(UUID.fromString(rs.getString("panel_id")), rs.getString("panel_name"), rs.getInt("mini_panel") == 1, rs.getInt("row_order"), UUID.fromString(rs.getString("product_id")), rs.getString("product_img"), rs.getString("product_name"), rs.getInt("product_price"), rs.getInt("min_offer"), rs.getInt("max_offer"));
    }
}
