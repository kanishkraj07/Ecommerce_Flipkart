package com.flipkartserver.authenticationservice.OtpAuthentication;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OtpAuthRowMapper implements RowMapper<OtpModel> {

    @Override
    public OtpModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OtpModel(rs.getLong("mobile_no"), rs.getInt("otp_no"));
    }
}
