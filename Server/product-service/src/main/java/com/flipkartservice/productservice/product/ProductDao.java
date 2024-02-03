package com.flipkartservice.productservice.product;

import com.flipkartservice.productservice.product.rowmapper.ProductDetailsByProductIdRm;
import com.flipkartservice.productservice.product.rowmapper.RawProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_PRODUCTS_WITH_PRODUCT_NAME = "select product_id, product_full_name from product_by_product_id";
    private static final String GET_PRODUCT_DETAILS_BY_PRODUCT_ID = "select * from product_by_product_id WHERE product_id=?";

    public List<RawProduct> getAllRawProducts() {
       return jdbcTemplate.query(GET_ALL_PRODUCTS_WITH_PRODUCT_NAME, new RawProductRowMapper());
    }

    public ProductDetails getProductById(UUID productId) {
        return jdbcTemplate.queryForObject(GET_PRODUCT_DETAILS_BY_PRODUCT_ID, new ProductDetailsByProductIdRm(), productId.toString());
    }
}