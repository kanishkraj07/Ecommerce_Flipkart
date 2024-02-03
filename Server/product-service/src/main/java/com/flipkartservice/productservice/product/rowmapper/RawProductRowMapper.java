package com.flipkartservice.productservice.product.rowmapper;


import com.flipkartservice.productservice.product.RawProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RawProductRowMapper implements RowMapper<RawProduct> {
    @Override
    public RawProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RawProduct(UUID.fromString(rs.getString("product_id")), rs.getString("product_full_name"));
    }
}

