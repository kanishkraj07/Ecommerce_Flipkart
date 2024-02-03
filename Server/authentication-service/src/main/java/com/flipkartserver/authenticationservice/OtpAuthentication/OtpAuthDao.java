package com.flipkartserver.authenticationservice.OtpAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OtpAuthDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OtpModel> getAll() {
        int number = 10;
        return jdbcTemplate.query("select * from otp_by_mobile_no where mobile_no=?", new OtpAuthRowMapper(), number);
    }
    public OtpModel getOtpAndMobileNo(Long mobileNo) {
        String checkMobileNoQuery = "select * from otp_by_mobile_no where mobile_no=?";
        return jdbcTemplate.queryForObject(checkMobileNoQuery, new OtpAuthRowMapper(), mobileNo);
    }

    public void updateOtp(OtpModel userOtp) {
        String updateOtpQuery = "update otp_by_mobile_no SET otp_no=? where mobile_no=?";
        jdbcTemplate.update(updateOtpQuery, userOtp.getOtp_no(), userOtp.getMobile_no());
    }

    public void saveOtp(OtpModel userOtp) {
        String saveOtpQuery = "insert into otp_by_mobile_no(mobile_no, otp_no) values(?,?)";
        jdbcTemplate.update(saveOtpQuery, userOtp.getMobile_no(), userOtp.getOtp_no());
    }
}
