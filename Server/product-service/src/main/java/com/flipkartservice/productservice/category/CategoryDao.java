package com.flipkartservice.productservice.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CategoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Category> getCategories() {
        String statement = "select * from category_item_by_category_id";
        return jdbcTemplate.query(statement, new CategoryRowMapper());
    }
    public List<CategoryPanelWIthProductModel> getAllPanelWithProducts() {
        String statement = "select category_panel_by_panel_id.panel_id, panel_name, mini_panel, row_order, product_id, product_img, product_name, product_price, min_offer, max_offer from product_db.category_panel_by_panel_id join product_by_category_panel_id on category_panel_by_panel_id.panel_id = product_by_category_panel_id.panel_id order by category_panel_by_panel_id.row_order asc ";
        return jdbcTemplate.query(statement, new CategoryPanelWithProductModelRm());
    }
}
