package com.flipkartservice.productservice.product.rowmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkartservice.productservice.product.ProductDetails;
import com.flipkartservice.productservice.product.SellerDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProductDetailsByProductIdRm implements RowMapper<ProductDetails> {
    @Override
    public ProductDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new ProductDetails(UUID.fromString(rs.getString("product_id")),
                rs.getString("product_full_name"), rs.getDouble("product_rating"),
                rs.getInt("total_ratings"), rs.getInt("total_reviews"),
                rs.getInt("flip_assurance") == 1, rs.getInt("extra_off"),
                rs.getDouble("final_price"), rs.getDouble("actual_price"),
                rs.getInt("off_percentage"), rs.getInt("packaging_fee"),
                rs.getDate("delivery_date"), rs.getInt("free_delivery") == 0,
                rs.getString("product_highlight").split(","), getSellerDetails(rs.getString("seller_details")), rs.getString("product_img"));
    }

    private SellerDetails getSellerDetails(String sellerJsonData) {
        try {
            return new ObjectMapper().readValue(sellerJsonData, SellerDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
